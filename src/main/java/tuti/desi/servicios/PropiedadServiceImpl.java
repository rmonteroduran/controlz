package tuti.desi.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tuti.desi.accesoDatos.IPropiedadRepo;
import tuti.desi.entidades.EstadoDisponibilidad;
import tuti.desi.entidades.HistorialEstadoPropiedad;
import tuti.desi.entidades.Propiedad;
import tuti.desi.entidades.TipoPropiedad;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PropiedadServiceImpl implements PropiedadService {

    @Autowired
    private IPropiedadRepo propiedadRepo;

    @Override
    public List<Propiedad> listarTodas() {
        // Trae solo las propiedades que no sufrieron baja lógica
        return propiedadRepo.findAllActivas();
    }

    @Override
    public List<Propiedad> listarDisponiblesParaPublicar() {
        return propiedadRepo.findDisponiblesParaPublicar();
    }

    @Override
    public List<Propiedad> listarConFiltros(String direccion, String ciudad, TipoPropiedad tipo, EstadoDisponibilidad estado) {
        return propiedadRepo.findConFiltros(
            (direccion != null && !direccion.trim().isEmpty()) ? direccion.trim() : null,
            (ciudad != null && !ciudad.trim().isEmpty()) ? ciudad.trim() : null,
            tipo,
            estado
        );
    }

    @Override
    public Propiedad buscarPorId(Long id) {
        return propiedadRepo.findById(id).orElse(null);
    }

    @Override
    public void guardar(Propiedad propiedad) {
        if (propiedad.getId() != null) {
            // MODIFICACIÓN
            Propiedad anterior = propiedadRepo.findById(propiedad.getId()).orElse(null);
            
            // Si hay cambio de estado se guarda en el historial
            if (anterior != null && anterior.getEstadoDisponibilidad() != propiedad.getEstadoDisponibilidad()) {
                propiedad.getHistorialEstados().add(
                    new HistorialEstadoPropiedad(propiedad.getEstadoDisponibilidad(), LocalDateTime.now(), propiedad)
                );
            }
        } else {
            // ALTA NUEVA
            propiedad.getHistorialEstados().add(
                new HistorialEstadoPropiedad(propiedad.getEstadoDisponibilidad(), LocalDateTime.now(), propiedad)
            );
        }
        
        // Guarda o actualiza en MySQL
        propiedadRepo.save(propiedad);
    }

    @Override
    public void eliminarLogico(Long id) {
        Propiedad p = buscarPorId(id);
        if (p != null) {
            p.setEliminada(true);
            propiedadRepo.save(p);
        }
    }

    @Override
    public boolean existeDireccionDuplicada(String direccion, Long id) {
        if (direccion == null || direccion.trim().isEmpty()) {
            return false;
        }
        String dirNormalizada = direccion.trim();
        if (id == null) {
            return propiedadRepo.existsByDireccionIgnoreCaseAndEliminadaFalse(dirNormalizada);
        } else {
            return propiedadRepo.existsByDireccionIgnoreCaseAndIdNotAndEliminadaFalse(dirNormalizada, id);
        }
    }
}
