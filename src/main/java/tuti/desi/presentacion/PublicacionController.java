package tuti.desi.presentacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tuti.desi.entidades.Publicacion;
import tuti.desi.entidades.EstadoPublicacion;
import tuti.desi.servicios.PublicacionService;
import tuti.desi.servicios.PropiedadService;

@Controller
@RequestMapping("/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;

    @Autowired
    private PropiedadService propiedadService;

    @GetMapping
    public String listar(
            @RequestParam(required = false) Long propiedadId,
            @RequestParam(required = false) EstadoPublicacion estado,
            @RequestParam(required = false) String ciudad,
            @RequestParam(required = false) Double precioMin,
            @RequestParam(required = false) Double precioMax,
            Model model) {

        model.addAttribute("publicaciones",
                publicacionService.buscarConFiltros(
                        propiedadId,
                        estado,
                        ciudad,
                        precioMin,
                        precioMax));

        model.addAttribute("propiedades",
                propiedadService.listarDisponiblesParaPublicar());

        model.addAttribute("estados",
                EstadoPublicacion.values());

        model.addAttribute("propiedadId", propiedadId);
        model.addAttribute("estado", estado);
        model.addAttribute("ciudad", ciudad);
        model.addAttribute("precioMin", precioMin);
        model.addAttribute("precioMax", precioMax);

        return "publicaciones/lista";
    }
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {

        Publicacion publicacion = publicacionService.buscarPorId(id);

        model.addAttribute("publicacion", publicacion);
        model.addAttribute("propiedades", propiedadService.listarDisponiblesParaPublicar());
        model.addAttribute("estados", EstadoPublicacion.values());

        return "publicaciones/formulario";
    }
    @GetMapping("/nueva")
    public String formularioAlta(Model model) {
        model.addAttribute("publicacion", new Publicacion());
        model.addAttribute("propiedades", propiedadService.listarDisponiblesParaPublicar());
        model.addAttribute("estados", EstadoPublicacion.values());
        return "publicaciones/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Publicacion publicacion,
                          Model model) {

        try {
            publicacionService.guardar(publicacion);
            return "redirect:/publicaciones";

        } catch (RuntimeException e) {

            model.addAttribute("error", e.getMessage());
            model.addAttribute("publicacion", publicacion);
            model.addAttribute("propiedades",
                    propiedadService.listarDisponiblesParaPublicar());
            model.addAttribute("estados",
                    EstadoPublicacion.values());

            return "publicaciones/formulario";
        }
    }
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, Model model) {

        try {
            publicacionService.eliminarLogico(id);
            return "redirect:/publicaciones";

        } catch (RuntimeException e) {

            model.addAttribute("publicaciones", publicacionService.listarTodas());
            model.addAttribute("error", e.getMessage());

            return "publicaciones/lista";
        }
    }
    
    
}
