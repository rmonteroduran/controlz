package tuti.desi.servicios;

import tuti.desi.entidades.Publicacion;
import java.util.List;

public interface PublicacionService {

    List<Publicacion> listarTodas();

    Publicacion buscarPorId(Long id);

    void guardar(Publicacion publicacion);

    void eliminarLogico(Long id);
    List<Publicacion> buscarConFiltros(
            Long propiedadId,
            tuti.desi.entidades.EstadoPublicacion estado,
            String ciudad,
            Double precioMin,
            Double precioMax);
}