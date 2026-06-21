package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tuti.desi.entidades.EstadoFactura;
import tuti.desi.entidades.Factura;
import java.time.LocalDate;
import java.util.List;

public interface IFacturaRepo extends JpaRepository<Factura, Long> {
	
	List<Factura> findByEliminadaFalse();

    List<Factura> findByContratoIdAndEliminadaFalse(Long contratoId);

    @Query("SELECT f FROM Factura f " +
           "LEFT JOIN f.contrato c " +
           "LEFT JOIN c.propiedad p " +
           "LEFT JOIN c.inquilino i " +
           "WHERE f.eliminada = false " +
           "AND (:contratoId IS NULL OR c.id = :contratoId) " +
           "AND (:propiedad IS NULL OR LOWER(COALESCE(p.direccion, '')) LIKE LOWER(CONCAT('%', :propiedad, '%'))) " +
           "AND (:inquilino IS NULL OR LOWER(COALESCE(i.apellido, '')) LIKE LOWER(CONCAT('%', :inquilino, '%'))) " +
           "AND (:estado IS NULL OR f.estado = :estado) " +
           "AND (:desde IS NULL OR f.fechaVencimiento >= :desde) " +
           "AND (:hasta IS NULL OR f.fechaVencimiento <= :hasta)")
    List<Factura> buscarConFiltros(
        @Param("contratoId") Long contratoId,
        @Param("propiedad") String propiedad,
        @Param("inquilino") String inquilino,
        @Param("estado") EstadoFactura estado,
        @Param("desde") LocalDate desde,
        @Param("hasta") LocalDate hasta
    );
}
