package tuti.desi.servicios;

import tuti.desi.entidades.EstadoDisponibilidad;
import tuti.desi.entidades.Propiedad;
import tuti.desi.entidades.TipoPropiedad;
import java.util.List;

public interface PropiedadService {
    List<Propiedad> listarTodas();
    List<Propiedad> listarDisponiblesParaPublicar();
    List<Propiedad> listarConFiltros(String direccion, String ciudad, TipoPropiedad tipo, EstadoDisponibilidad estado);
    Propiedad buscarPorId(Long id);
    void guardar(Propiedad propiedad);
    void eliminarLogico(Long id);
    boolean existeDireccionDuplicada(String direccion, Long id);
}