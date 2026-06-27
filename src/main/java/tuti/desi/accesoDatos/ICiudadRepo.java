package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tuti.desi.entidades.Ciudad;

@Repository
public interface ICiudadRepo extends JpaRepository<Ciudad, Long> {
}