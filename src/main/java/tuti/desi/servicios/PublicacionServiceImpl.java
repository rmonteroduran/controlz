package tuti.desi.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tuti.desi.accesoDatos.IPublicacionRepo;
import tuti.desi.entidades.Publicacion;
import java.util.List;

@Service
public class PublicacionServiceImpl implements PublicacionService {

    @Autowired
    private IPublicacionRepo publicacionRepo;

    @Override
    public List<Publicacion> listarTodas() {
        return publicacionRepo.findAllActivas();
    }

    @Override
    public Publicacion buscarPorId(Long id) {
        return publicacionRepo.findById(id).orElse(null);
    }

    @Override
    public void guardar(Publicacion publicacion) {
        publicacionRepo.save(publicacion);
    }

    @Override
    public void eliminarLogico(Long id) {
        Publicacion p = buscarPorId(id);

        if (p != null) {
            p.setEliminada(true);
            publicacionRepo.save(p);
        }
    }
}