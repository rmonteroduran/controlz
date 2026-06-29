package tuti.desi.presentacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tuti.desi.entidades.EstadoDisponibilidad;
import tuti.desi.entidades.Propiedad;
import tuti.desi.entidades.TipoPropiedad;
import tuti.desi.servicios.PersonaService;
import tuti.desi.servicios.PropiedadService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/propiedades")
public class PropiedadController {

    @Autowired
    private PropiedadService propiedadService;

    @Autowired
    private PersonaService personaService;
    
    @Autowired
    private tuti.desi.accesoDatos.ICiudadRepo ciudadRepo;

    // 1. CONSULTA / LISTADO: Muestra la tabla con filtros
    @GetMapping
    public String listar(
            @RequestParam(required = false) String direccion,
            @RequestParam(required = false) String ciudad,
            @RequestParam(required = false) TipoPropiedad tipo,
            @RequestParam(required = false) EstadoDisponibilidad estado,
            Model model) {
        
        model.addAttribute("propiedades", propiedadService.listarConFiltros(direccion, ciudad, tipo, estado));
        model.addAttribute("direccion", direccion);
        model.addAttribute("ciudad", ciudad);
        model.addAttribute("tipoSeleccionado", tipo);
        model.addAttribute("estadoSeleccionado", estado);
        model.addAttribute("tipos", TipoPropiedad.values());
        model.addAttribute("estados", EstadoDisponibilidad.values());
        return "propiedades/lista";
    }

    // 2. FORMULARIO DE ALTA:
    @GetMapping("/nueva")
    public String formularioAlta(Model model) {
        model.addAttribute("propiedad", new Propiedad());
        model.addAttribute("tipos", TipoPropiedad.values());
        model.addAttribute("estados", EstadoDisponibilidad.values());
        model.addAttribute("propietarios", personaService.listarTodasActivas());
        model.addAttribute("ciudades", ciudadRepo.findAll());
        return "propiedades/formulario";
    }

    // 3. FORMULARIO DE EDICIÓN: Busca la propiedad seleccionada por ID y la carga en el formulario
    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        Propiedad propiedad = propiedadService.buscarPorId(id);
        model.addAttribute("propiedad", propiedad);
        model.addAttribute("tipos", TipoPropiedad.values());
        model.addAttribute("estados", EstadoDisponibilidad.values());
        model.addAttribute("propietarios", personaService.listarTodasActivas());
        model.addAttribute("ciudades", ciudadRepo.findAll());
        return "propiedades/formulario";
    }

    // 4. GUARDAR: Procesa los datos del formulario (con validaciones de alta y dirección única)
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Propiedad propiedad, Model model) {
        // Validación de campos requeridos
        if (propiedad.getDireccion() == null || propiedad.getDireccion().trim().isEmpty() ||
        	propiedad.getCiudad() == null || propiedad.getCiudad().getId() == null ||
            propiedad.getPropietario() == null || propiedad.getPropietario().getId() == null ||
            propiedad.getTipo() == null ||
            propiedad.getEstadoDisponibilidad() == null ||
            propiedad.getCantidadAmbientes() == null || propiedad.getCantidadAmbientes() < 1 ||
            propiedad.getMetrosCuadrados() == null || propiedad.getMetrosCuadrados() < 1 ||
            propiedad.getDescripcion() == null || propiedad.getDescripcion().trim().isEmpty()) {
            
            model.addAttribute("error", "Todos los campos son obligatorios y deben ser válidos.");
            model.addAttribute("propiedad", propiedad);
            model.addAttribute("tipos", TipoPropiedad.values());
            model.addAttribute("estados", EstadoDisponibilidad.values());
            model.addAttribute("propietarios", personaService.listarTodasActivas());
            model.addAttribute("ciudades", ciudadRepo.findAll());
            return "propiedades/formulario";
        }

        // Valida dirección ya existente
        if (propiedad.getId() == null && propiedadService.existeDireccionDuplicada(propiedad.getDireccion(), propiedad.getId())) {
            model.addAttribute("error", "Ya existe una propiedad con la dirección especificada.");
            model.addAttribute("propiedad", propiedad);
            model.addAttribute("tipos", TipoPropiedad.values());
            model.addAttribute("estados", EstadoDisponibilidad.values());
            model.addAttribute("propietarios", personaService.listarTodasActivas());
            model.addAttribute("ciudades", ciudadRepo.findAll());
            return "propiedades/formulario";
        }

        propiedadService.guardar(propiedad);
        return "redirect:/propiedades"; // Redirige automáticamente al listado
    }

    // 5. BAJA LÓGICA:
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            propiedadService.eliminarLogico(id);
            redirectAttributes.addFlashAttribute("exito", "La propiedad fue eliminada correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/propiedades";
    }
}
