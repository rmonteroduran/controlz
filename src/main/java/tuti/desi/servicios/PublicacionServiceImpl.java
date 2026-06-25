package tuti.desi.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import tuti.desi.accesoDatos.IPublicacionRepo;
import tuti.desi.entidades.EstadoDisponibilidad;
import tuti.desi.entidades.EstadoPublicacion;
import tuti.desi.entidades.Propiedad;
import tuti.desi.entidades.Publicacion;

@Service
public class PublicacionServiceImpl implements PublicacionService {

    @Autowired
    private IPublicacionRepo publicacionRepo;

    @Autowired
    private PropiedadService propiedadService;

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

        // Validación de fecha
        if (publicacion.getFechaPublicacion() == null) {
            throw new RuntimeException("Debe ingresar una fecha.");
        }

        if (publicacion.getFechaPublicacion().isBefore(java.time.LocalDate.now())) {
            throw new RuntimeException(
                    "La fecha de publicación no puede ser anterior al día de hoy.");
        }

        // Validación del precio
        if (publicacion.getPrecioMensual() == null
                || publicacion.getPrecioMensual() <= 0) {

            throw new RuntimeException(
                    "El precio mensual debe ser mayor a cero");
        }

        // Obtiene la propiedad completa desde la base de datos
        Propiedad propiedad = propiedadService.buscarPorId(
                publicacion.getPropiedad().getId());

        if (propiedad == null) {
            throw new RuntimeException("La propiedad no existe");
        }

        // La propiedad debe estar disponible
        if (propiedad.getEstadoDisponibilidad() != EstadoDisponibilidad.DISPONIBLE) {

            throw new RuntimeException(
                    "La propiedad debe estar disponible para publicarse");
        }

        // Si es una modificación, no permitir cambiar condiciones
        // cuando la publicación está FINALIZADA
        if (publicacion.getId() != null) {

            Publicacion anterior = buscarPorId(publicacion.getId());

            if (anterior != null
                    && anterior.getEstadoPublicacion() == EstadoPublicacion.FINALIZADA) {

                if (!anterior.getCondiciones().equals(publicacion.getCondiciones())) {

                    throw new RuntimeException(
                            "No pueden modificarse las condiciones de una publicación finalizada.");
                }
            }
        }

        // No puede existir otra publicación activa para la misma propiedad
        if (publicacion.getEstadoPublicacion() == EstadoPublicacion.ACTIVA) {

            boolean existe;

            if (publicacion.getId() == null) {

                // Alta
                existe = publicacionRepo.existePublicacionActiva(propiedad.getId());

            } else {

                // Modificación
                existe = publicacionRepo.existeOtraPublicacionActiva(
                        propiedad.getId(),
                        publicacion.getId());
            }

            if (existe) {
                throw new RuntimeException(
                        "Ya existe una publicación activa para esta propiedad");
            }
        }

        // Asigna la propiedad completa
        publicacion.setPropiedad(propiedad);

        publicacionRepo.save(publicacion);
    }

    @Override
    public List<Publicacion> buscarConFiltros(
            Long propiedadId,
            EstadoPublicacion estado,
            String ciudad,
            Double precioMin,
            Double precioMax) {

        return publicacionRepo.buscarConFiltros(
                propiedadId,
                estado,
                ciudad,
                precioMin,
                precioMax);
    }

    @Override
    public void eliminarLogico(Long id) {

        Publicacion p = buscarPorId(id);

        if (p == null) {
            throw new RuntimeException("La publicación no existe");
        }

        if (p.getEstadoPublicacion() != EstadoPublicacion.ACTIVA) {
            throw new RuntimeException(
                    "Solo pueden eliminarse publicaciones activas");
        }

        p.setEliminada(true);

        publicacionRepo.save(p);
    }
}