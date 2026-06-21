package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import tuti.desi.entidades.Factura;
import java.util.List;


public interface IFacturaRepo extends JpaRepository<Factura, Long> {
	
	List<Factura> findByEliminadaFalse();
    
    List<Factura> findByContratoIdAndEliminadaFalse(Long contratoId);
}
