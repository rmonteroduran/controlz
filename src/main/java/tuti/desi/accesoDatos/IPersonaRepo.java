package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tuti.desi.entidades.Persona;
import java.util.List;

@Repository
public interface IPersonaRepo extends JpaRepository<Persona, Long> {
    
    @Query("SELECT p FROM Persona p WHERE p.eliminada = false")
    List<Persona> findAllActivas();
}
