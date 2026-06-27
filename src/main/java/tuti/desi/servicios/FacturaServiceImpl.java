package tuti.desi.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tuti.desi.accesoDatos.IFacturaRepo;
import tuti.desi.accesoDatos.IHistorialFacturaRepo;
import tuti.desi.entidades.EstadoContrato;
import tuti.desi.entidades.EstadoFactura;
import tuti.desi.entidades.Factura;
import tuti.desi.entidades.HistorialEstadoFactura;
import java.util.List;
import tuti.desi.entidades.Contrato;


@Service
public class FacturaServiceImpl implements FacturaService {
	
	 @Autowired
	    private IFacturaRepo facturaRepo;

	    @Autowired
	    private IHistorialFacturaRepo historialRepo;
	    
	    @Autowired
	    private tuti.desi.accesoDatos.IContratoRepo contratoRepo;

	    @Override
	    public void crear(Factura factura) {
	    	 if (factura.getContrato() == null || factura.getContrato().getId() == null) {
	    	        throw new IllegalArgumentException("La factura debe tener un contrato asociado.");
	    	    }

	    	    Contrato contrato = contratoRepo.findById(factura.getContrato().getId())
	    	        .orElseThrow(() -> new IllegalArgumentException("Contrato no encontrado."));
	    	    factura.setContrato(contrato);

	    	    EstadoContrato estadoContrato = contrato.getEstado();
	    	    if (estadoContrato == EstadoContrato.FINALIZADO ||
	    	        estadoContrato == EstadoContrato.RESCINDIDO ||
	    	        estadoContrato == EstadoContrato.BORRADOR ||
	    	        contrato.isEliminado()) {
	    	        throw new IllegalArgumentException("No se puede crear una factura para un contrato que no está activo.");
	    	    }

	    	    if (factura.getFechaEmision() == null || factura.getFechaVencimiento() == null) {
	    	        throw new IllegalArgumentException("Las fechas de emisión y vencimiento son obligatorias.");
	    	    }
	    	    if (factura.getFechaVencimiento().isBefore(factura.getFechaEmision())) {
	    	        throw new IllegalArgumentException("La fecha de vencimiento debe ser igual o posterior a la fecha de emisión.");
	    	    }

	    	    if (factura.getImporte() == null || factura.getImporte() <= 0) {
	    	        throw new IllegalArgumentException("El importe debe ser mayor a cero.");
	    	    }

	    	    factura.setEstado(EstadoFactura.PENDIENTE);

	    	    factura.setFechaPago(null);
	    	    factura.setMedioDePago(null);
	    	    factura.setImportePago(null);
	    	    factura.setInteres(null);

	    	    facturaRepo.save(factura);
	    	    historialRepo.save(new HistorialEstadoFactura(factura, EstadoFactura.PENDIENTE));
	    	}

	    @Override
	    public void modificar(Factura factura) {
	        Factura existente = facturaRepo.findById(factura.getId())
	            .orElseThrow(() -> new IllegalArgumentException("Factura no encontrada."));

	        if (existente.getEstado() == EstadoFactura.PAGADA ||
	            existente.getEstado() == EstadoFactura.ANULADA) {
	            throw new IllegalArgumentException("No se puede modificar una factura pagada o anulada.");
	        }

	        factura.setContrato(existente.getContrato());

	        if (factura.getFechaEmision() == null || factura.getFechaVencimiento() == null) {
	            throw new IllegalArgumentException("Las fechas de emisión y vencimiento son obligatorias.");
	        }
	        if (factura.getFechaVencimiento().isBefore(factura.getFechaEmision())) {
	            throw new IllegalArgumentException("La fecha de vencimiento debe ser igual o posterior a la fecha de emisión.");
	        }

	        if (factura.getImporte() == null || factura.getImporte() <= 0) {
	            throw new IllegalArgumentException("El importe debe ser mayor a cero.");
	        }

	        if (existente.getEstado() != factura.getEstado()) {
	            validarTransicion(existente.getEstado(), factura.getEstado());
	        }

	        if (factura.getEstado() == EstadoFactura.PAGADA) {
	            if (factura.getFechaPago() == null ||
	                factura.getMedioDePago() == null ||
	                factura.getImportePago() == null ||
	                factura.getImportePago() <= 0) {
	                throw new IllegalArgumentException("Para registrar el pago debe completar todos los datos: fecha, medio e importe positivo.");
	            }
	        }

	        if (factura.getEstado() != EstadoFactura.PAGADA) {
	            factura.setFechaPago(null);
	            factura.setMedioDePago(null);
	            factura.setImportePago(null);
	            factura.setInteres(null);
	        }

	        facturaRepo.save(factura);

	        if (existente.getEstado() != factura.getEstado()) {
	            historialRepo.save(new HistorialEstadoFactura(factura, factura.getEstado()));
	        }
	    }

	    @Override
	    public void eliminar(Long id) {
	        Factura factura = facturaRepo.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("Factura no encontrada."));

	        if (factura.getEstado() == EstadoFactura.PAGADA) {
	            throw new IllegalArgumentException("No se puede eliminar una factura pagada.");
	        }

	        factura.setEliminada(true);
	        facturaRepo.save(factura);
	    }

	    @Override
	    public List<Factura> listarActivas() {
	        return facturaRepo.findByEliminadaFalse();
	    }

	    @Override
	    public Factura buscarPorId(Long id) {
	        return facturaRepo.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("Factura no encontrada."));
	    }

	    @Override
	    public List<Factura> listarPorContrato(Long contratoId) {
	        return facturaRepo.findByContratoIdAndEliminadaFalse(contratoId);
	    }
	    
	    
	    private void validarTransicion(EstadoFactura estadoActual, EstadoFactura estadoNuevo) {
	        boolean transicionValida = false;

	        switch (estadoActual) {
	            case PENDIENTE:
	                transicionValida = estadoNuevo == EstadoFactura.PAGADA ||
	                                   estadoNuevo == EstadoFactura.VENCIDA ||
	                                   estadoNuevo == EstadoFactura.ANULADA;
	                break;
	            case VENCIDA:
	                transicionValida = estadoNuevo == EstadoFactura.PAGADA;
	                break;
	            default:
	                transicionValida = false;
	        }

	        if (!transicionValida) {
	            throw new IllegalArgumentException(
	                "Transición de estado inválida: " + estadoActual + " → " + estadoNuevo);
	        }
	    }
	    
	    @Override
	    public List<Factura> buscarConFiltros(Long contratoId, String propiedad, String inquilino,
	                                           EstadoFactura estado, java.time.LocalDate desde, java.time.LocalDate hasta) {
	        // Normalizar strings vacíos a null
	        if (propiedad != null && propiedad.isBlank()) propiedad = null;
	        if (inquilino != null && inquilino.isBlank()) inquilino = null;

	        return facturaRepo.buscarConFiltros(contratoId, propiedad, inquilino, estado, desde, hasta);
	    }
	}