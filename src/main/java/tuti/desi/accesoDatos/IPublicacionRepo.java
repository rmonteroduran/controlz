package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tuti.desi.entidades.Publicacion;
import java.util.List;

@Repository
public interface IPublicacionRepo extends JpaRepository<Publicacion, Long> {

    @Query("SELECT p FROM Publicacion p WHERE p.eliminada = false")
    List<Publicacion> findAllActivas();
    @Query("""
    		SELECT COUNT(p) > 0
    		FROM Publicacion p
    		WHERE p.eliminada = false
    		AND p.estadoPublicacion = 'ACTIVA'
    		AND p.propiedad.id = :propiedadId
    		""")
    		boolean existePublicacionActiva(Long propiedadId);
}
