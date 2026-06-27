package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tuti.desi.entidades.Contrato;
import tuti.desi.entidades.EstadoContrato;

import java.time.LocalDate;
import java.util.List;

public interface IContratoRepo extends JpaRepository<Contrato, Long> {

    // Filtrar contratos omitiendo los eliminados lógicamente.
    // Los filtros que vengan en null se ignoran
    @Query("SELECT c FROM Contrato c WHERE c.eliminado = false " +
            "AND (:propiedadId IS NULL OR c.propiedad.id = :propiedadId) " +
            "AND (:inquilinoId IS NULL OR c.inquilino.id = :inquilinoId) " +
            "AND (:estado IS NULL OR c.estado = :estado) " +
            "AND (:fechaInicio IS NULL OR c.fechaInicio = :fechaInicio)")
    List<Contrato> buscarConFiltros(@Param("propiedadId") Long propiedadId,
                                    @Param("inquilinoId") Long inquilinoId,
                                    @Param("estado") EstadoContrato estado,
                                    @Param("fechaInicio") LocalDate fechaInicio);
    
    // Validar si una propiedad ya tiene un contrato activo y vigente.
    @Query("SELECT COUNT(c) > 0 FROM Contrato c WHERE c.propiedad.id = :propiedadId AND c.estado = :estado AND c.eliminado = false")
    boolean propiedadValidaDisponible(@Param("propiedadId") Long propiedadId, @Param("estado") EstadoContrato estado);
}