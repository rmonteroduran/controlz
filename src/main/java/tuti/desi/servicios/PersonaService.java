package tuti.desi.servicios;

import tuti.desi.entidades.Persona;
import java.util.List;

public interface PersonaService {
    List<Persona> listarTodasActivas();
    Persona buscarPorId(Long id);
    void guardar(Persona persona);
    void eliminarLogico(Long id);
}
