package tuti.desi.servicios;

import tuti.desi.entidades.EstadoFactura;
import tuti.desi.entidades.Factura;
import java.time.LocalDate;
import java.util.List;

public interface FacturaService {

	 void crear(Factura factura);

	    void modificar(Factura factura);

	    void eliminar(Long id);

	    List<Factura> listarActivas();

	    Factura buscarPorId(Long id);

	    List<Factura> listarPorContrato(Long contratoId);

	    List<Factura> buscarConFiltros(Long contratoId, String propiedad, String inquilino, EstadoFactura estado, LocalDate desde, LocalDate hasta);
    
}
