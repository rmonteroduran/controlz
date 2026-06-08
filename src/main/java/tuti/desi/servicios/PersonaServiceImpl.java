package tuti.desi.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tuti.desi.accesoDatos.IPersonaRepo;
import tuti.desi.entidades.Persona;
import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private IPersonaRepo personaRepo;

    @Override
    public List<Persona> listarTodasActivas() {
        return personaRepo.findAllActivas();
    }

    @Override
    public Persona buscarPorId(Long id) {
        return personaRepo.findById(id).orElse(null);
    }

    @Override
    public void guardar(Persona persona) {
        personaRepo.save(persona);
    }

    @Override
    public void eliminarLogico(Long id) {
        Persona p = buscarPorId(id);
        if (p != null) {
            p.setEliminada(true);
            personaRepo.save(p);
        }
    }
}
