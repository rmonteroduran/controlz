package tuti.desi.accesoDatos;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tuti.desi.entidades.Publicacion;

@Repository
public interface IPublicacionRepo extends JpaRepository<Publicacion, Long> {

    @Query("SELECT p FROM Publicacion p WHERE p.eliminada = false")
    List<Publicacion> findAllActivas();

    // Para ALTA
    @Query("""
            SELECT COUNT(p) > 0
            FROM Publicacion p
            WHERE p.eliminada = false
            AND p.estadoPublicacion = 'ACTIVA'
            AND p.propiedad.id = :propiedadId
            """)
    boolean existePublicacionActiva(Long propiedadId);

    // Para MODIFICACIÓN
    @Query("""
            SELECT COUNT(p) > 0
            FROM Publicacion p
            WHERE p.eliminada = false
            AND p.estadoPublicacion = 'ACTIVA'
            AND p.propiedad.id = :propiedadId
            AND p.id <> :publicacionId
            """)
    boolean existeOtraPublicacionActiva(Long propiedadId, Long publicacionId);
    @Query("""
    		SELECT p
    		FROM Publicacion p
    		WHERE p.eliminada = false
    		AND (:propiedadId IS NULL OR p.propiedad.id = :propiedadId)
    		AND (:estado IS NULL OR p.estadoPublicacion = :estado)
    		AND (:ciudad IS NULL OR :ciudad = '' OR LOWER(p.propiedad.ciudad.nombre) LIKE LOWER(CONCAT('%', :ciudad, '%')))
    		AND (:precioMin IS NULL OR p.precioMensual >= :precioMin)
    		AND (:precioMax IS NULL OR p.precioMensual <= :precioMax)
    		""")
    		List<Publicacion> buscarConFiltros(
    		        Long propiedadId,
    		        tuti.desi.entidades.EstadoPublicacion estado,
    		        String ciudad,
    		        BigDecimal precioMin,
    		        BigDecimal precioMax);
}