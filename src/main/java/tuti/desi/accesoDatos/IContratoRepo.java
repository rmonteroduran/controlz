package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import tuti.desi.entidades.Contrato;

public interface IContratoRepo extends JpaRepository<Contrato, Long> {

}
