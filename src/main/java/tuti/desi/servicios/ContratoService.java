package tuti.desi.servicios;

import tuti.desi.entidades.EstadoContrato;
import tuti.desi.entidades.Contrato;
import java.time.LocalDate;
import java.util.List;

public interface ContratoService {
    // Registrar un nuevo contrato en el sistema.
    // Debe aplicar validaciones de negocio y cambiar el estado de la propiedad a ALQUILADA.
    Contrato crearContrato(Contrato contrato) throws Exception;

    // Modificar los datos de un contrato existente respetando las transiciones de estado.
    // Si el contrato se finaliza o rescinde, la propiedad debe volver a DISPONIBLE.
    Contrato modificarContrato(Contrato contrato) throws Exception;

    // Realizar la baja lógica de un contrato cambiando el flag 'eliminado' a true.
    // Solo se permite si el contrato está en estado BORRADOR.
    void eliminarContrato(Long id) throws Exception;

    // Consultar un contrato por su id
    Contrato buscarPorId(Long id) throws Exception;

    // Permite listar los contratos aplicando filtros opcionales.
    // Excluye los contratos que fueron eliminados lógicamente.
    List<Contrato> buscarConFiltros(Long propiedadId, Long inquilinoId, EstadoContrato estado, LocalDate fechaInicio);
}
