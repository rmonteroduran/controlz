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
    public String listar(Model model) {
        model.addAttribute("publicaciones", publicacionService.listarTodas());
        return "publicaciones/lista";
    }
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {

        Publicacion publicacion = publicacionService.buscarPorId(id);

        model.addAttribute("publicacion", publicacion);
        model.addAttribute("propiedades", propiedadService.listarTodas());
        model.addAttribute("estados", EstadoPublicacion.values());

        return "publicaciones/formulario";
    }
    @GetMapping("/nueva")
    public String formularioAlta(Model model) {
        model.addAttribute("publicacion", new Publicacion());
        model.addAttribute("propiedades", propiedadService.listarTodas());
        model.addAttribute("estados", EstadoPublicacion.values());
        return "publicaciones/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Publicacion publicacion) {
        publicacionService.guardar(publicacion);
        return "redirect:/publicaciones";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        publicacionService.eliminarLogico(id);
        return "redirect:/publicaciones";
    }
}