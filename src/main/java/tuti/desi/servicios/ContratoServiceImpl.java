package tuti.desi.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tuti.desi.accesoDatos.IContratoRepo;
import tuti.desi.entidades.Contrato;
import tuti.desi.entidades.EstadoContrato;


import tuti.desi.entidades.EstadoDisponibilidad;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ContratoServiceImpl implements ContratoService {

    @Autowired
    private IContratoRepo contratoRepo;

    @Autowired
    private PropiedadService propiedadService;
    
    @Override
    @Transactional
    public Contrato crearContrato(Contrato contrato) throws Exception {
        // Validaciones obligatorias
        if (contrato.getImporteMensual() == null || contrato.getImporteMensual().compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("El importe mensual debe ser un número positivo.");
        }
        if (contrato.getDuracionMeses() == null || contrato.getDuracionMeses() <= 0) {
            throw new Exception("La duración en meses debe ser un número positivo.");
        }
        if (contrato.getDiaVencimiento() == null || contrato.getDiaVencimiento() < 1 || contrato.getDiaVencimiento() > 31) {
            throw new Exception("El día de vencimiento mensual debe ser un número válido entre 1 y 31.");
        }
        if (contrato.getPropiedad() == null || contrato.getInquilino() == null || contrato.getFechaInicio() == null) {
            throw new Exception("Todos los datos del contrato (Propiedad, Inquilino, Fecha de Inicio) son requeridos.");
        }

        // Estado por defecto cuando se crea si no viene especificado: borrador
        if (contrato.getEstado() == null) {
            contrato.setEstado(EstadoContrato.BORRADOR);
        }

        // Si se intenta dar de alta directamente como ACTIVO, aplicamos las restricciones
        if (contrato.getEstado() == EstadoContrato.ACTIVO) {
            validarPropiedadParaActivacion(contrato.getPropiedad().getId());
            
            // Efecto colateral: Cambia la propiedad a ALQUILADA
            contrato.getPropiedad().setEstadoDisponibilidad(EstadoDisponibilidad.ALQUILADA);
            propiedadService.guardar(contrato.getPropiedad()); 
        }

        // Registra automáticamente el estado inicial en la tabla intermedia de historial
        contrato.registrarCambioEstado(contrato.getEstado());

        return contratoRepo.save(contrato);
    }

    @Override
    @Transactional
    public Contrato modificarContrato(Contrato contrato) throws Exception {
        if (contrato.getId() == null) {
            throw new Exception("No se puede modificar un contrato que no posea un id valido.");
        }

        // Recuperar el estado actual desde la BD antes de pisarlo para evaluar la transición
        Contrato contratoExistente = contratoRepo.findById(contrato.getId())
                .orElseThrow(() -> new Exception("Contrato no encontrado en el sistema."));

        // Proteccion contra nulos
        if (contrato.getImporteMensual() == null || contrato.getImporteMensual().compareTo(BigDecimal.ZERO) <= 0) throw new Exception("El importe mensual debe ser positivo.");
        if (contrato.getDuracionMeses() == null || contrato.getDuracionMeses() <= 0) throw new Exception("La duración debe ser positiva.");
        if (contrato.getDiaVencimiento() == null || contrato.getDiaVencimiento() < 1 || contrato.getDiaVencimiento() > 31) throw new Exception("Día de vencimiento inválido.");

        EstadoContrato estadoAnterior = contratoExistente.getEstado();
        EstadoContrato nuevoEstado = contrato.getEstado();

        // Control del flujo y transiciones de estado
        if (estadoAnterior != nuevoEstado) {
            
            // Transicion: no se permite volver de FINALIZADO o RESCINDIDO a ACTIVO
            if (estadoAnterior == EstadoContrato.FINALIZADO || estadoAnterior == EstadoContrato.RESCINDIDO) {
                throw new Exception("No se puede modificar el estado de un contrato finalizado o rescindido.");
            }

            // Transición: BORRADOR -> ACTIVO
            if (estadoAnterior == EstadoContrato.BORRADOR && nuevoEstado == EstadoContrato.ACTIVO) {
                validarPropiedadParaActivacion(contrato.getPropiedad().getId());
                contrato.getPropiedad().setEstadoDisponibilidad(EstadoDisponibilidad.ALQUILADA);
                propiedadService.guardar(contrato.getPropiedad()); // guardar cambios en propiedad
            }
            
            // Transición: ACTIVO -> FINALIZADO o RESCINDIDO
            if (estadoAnterior == EstadoContrato.ACTIVO && (nuevoEstado == EstadoContrato.FINALIZADO || nuevoEstado == EstadoContrato.RESCINDIDO)) {
                // La propiedad vuelve a quedar libre
                contrato.getPropiedad().setEstadoDisponibilidad(EstadoDisponibilidad.DISPONIBLE);
                propiedadService.guardar(contrato.getPropiedad()); // guardar cambios en propiedad
            }

            // Impacta automáticamente en la lista interna de historial que creamos en la entidad
            contratoExistente.registrarCambioEstado(nuevoEstado);
        }

        // Actualizamos los campos editables permitidos
        contratoExistente.setFechaInicio(contrato.getFechaInicio());
        contratoExistente.setDuracionMeses(contrato.getDuracionMeses());
        contratoExistente.setImporteMensual(contrato.getImporteMensual());
        contratoExistente.setDiaVencimiento(contrato.getDiaVencimiento());
        contratoExistente.setDescripcion(contrato.getDescripcion());
        contratoExistente.setEstado(nuevoEstado);
        contratoExistente.setInquilino(contrato.getInquilino());
        contratoExistente.setPropiedad(contrato.getPropiedad());

        return contratoRepo.save(contratoExistente);
    }

    @Override
    @Transactional
    public void eliminarContrato(Long id) throws Exception {
        Contrato contrato = contratoRepo.findById(id)
                .orElseThrow(() -> new Exception("Contrato no encontrado."));

        // HU 3.2: Solo pueden eliminarse contratos en estado BORRADOR
        if (contrato.getEstado() != EstadoContrato.BORRADOR) {
            throw new Exception("No se puede eliminar el contrato. Motivo: Solo se permite la baja si el contrato está en estado BORRADOR.");
        }

        // Baja lógica (No altera el estado de la propiedad)
        contrato.setEliminado(true);
        contratoRepo.save(contrato);
    }

    @Override
    @Transactional(readOnly = true)
    public Contrato buscarPorId(Long id) throws Exception {
        return contratoRepo.findById(id)
                .orElseThrow(() -> new Exception("Contrato no encontrado."));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contrato> buscarConFiltros(Long propiedadId, Long inquilinoId, EstadoContrato estado, LocalDate fechaInicio) {
        // Excluye automáticamente los eliminados a través de la query del Repositorio
        return contratoRepo.buscarConFiltros(propiedadId, inquilinoId, estado, fechaInicio);
    }

    // Validación para evitar duplicidad de contratos activos en una propiedad
    private void validarPropiedadParaActivacion(Long propiedadId) throws Exception {
        // Validar que esté estructuralmente DISPONIBLE
        if (propiedadService.buscarPorId(propiedadId).getEstadoDisponibilidad() != EstadoDisponibilidad.DISPONIBLE) {
            throw new Exception("No se puede activar el contrato porque la propiedad no se encuentra DISPONIBLE.");
        }
        // Validar que no haya disputas con otro contrato activo en BD
        boolean yaTieneContratoActivo = contratoRepo.propiedadValidaDisponible(propiedadId, EstadoContrato.ACTIVO);
        if (yaTieneContratoActivo) {
            throw new Exception("La propiedad seleccionada ya posee otro contrato ACTIVO vigente. No se puede activar este contrato.");
        }
    }
	
}