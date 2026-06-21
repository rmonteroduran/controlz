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

@Service
public class FacturaServiceImpl implements FacturaService {
	
	@Autowired
    private IFacturaRepo facturaRepo;

    @Autowired
    private IHistorialFacturaRepo historialRepo;

    @Override
    public void crear(Factura factura) {
        if (factura.getContrato() == null) {
            throw new IllegalArgumentException("La factura debe tener un contrato asociado.");
        }

        EstadoContrato estadoContrato = factura.getContrato().getEstado();
        if (estadoContrato == EstadoContrato.FINALIZADO ||
            estadoContrato == EstadoContrato.RESCINDIDO ||
            estadoContrato == EstadoContrato.BORRADOR ||
            factura.getContrato().isEliminada()) {
            throw new IllegalArgumentException("No se puede crear una factura para un contrato que no está activo.");
        }

        factura.setEstado(EstadoFactura.PENDIENTE);

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

        validarTransicion(existente.getEstado(), factura.getEstado());

        if (factura.getEstado() == EstadoFactura.PAGADA) {
            if (factura.getFechaPago() == null ||
                factura.getMedioDePago() == null ||
                factura.getImportePago() == null ||
                factura.getImportePago() <= 0) {
                throw new IllegalArgumentException("Para registrar el pago debe completar todos los datos de pago.");
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
                "Transición de estado inválida: " + estadoActual + " -> " + estadoNuevo);
        }
    }

}
