package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import tuti.desi.entidades.HistorialEstadoFactura;
import java.util.List;


public interface IHistorialFacturaRepo extends JpaRepository<HistorialEstadoFactura, Long> {

    List<HistorialEstadoFactura> findByFacturaId(Long facturaId);

}
