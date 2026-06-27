package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tuti.desi.entidades.EstadoDisponibilidad;
import tuti.desi.entidades.Propiedad;
import tuti.desi.entidades.TipoPropiedad;
import java.util.List;

@Repository
public interface IPropiedadRepo extends JpaRepository<Propiedad, Long> {
    
    // Consulta para traer solo las propiedades que NO sufrieron una baja lógica
    @Query("SELECT p FROM Propiedad p WHERE p.eliminada = false")
    List<Propiedad> findAllActivas();

    @Query("SELECT p FROM Propiedad p WHERE p.eliminada = false AND p.estadoDisponibilidad = 'DISPONIBLE'")
    List<Propiedad> findDisponiblesParaPublicar();

    @Query("SELECT p FROM Propiedad p WHERE p.eliminada = false " +
           "AND (:direccion IS NULL OR :direccion = '' OR LOWER(p.direccion) LIKE LOWER(CONCAT('%', :direccion, '%'))) " +
           "AND (:ciudad IS NULL OR :ciudad = '' OR LOWER(p.ciudad) LIKE LOWER(CONCAT('%', :ciudad, '%'))) " +
           "AND (:tipo IS NULL OR p.tipo = :tipo) " +
           "AND (:estado IS NULL OR p.estadoDisponibilidad = :estado)")
    List<Propiedad> findConFiltros(
        @Param("direccion") String direccion,
        @Param("ciudad") String ciudad,
        @Param("tipo") TipoPropiedad tipo,
        @Param("estado") EstadoDisponibilidad estado
    );

    boolean existsByDireccionIgnoreCaseAndEliminadaFalse(String direccion);
    
    boolean existsByDireccionIgnoreCaseAndIdNotAndEliminadaFalse(String direccion, Long id);
}