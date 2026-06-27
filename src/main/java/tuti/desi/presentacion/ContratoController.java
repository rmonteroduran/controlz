package tuti.desi.presentacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tuti.desi.entidades.Contrato;
import tuti.desi.entidades.EstadoContrato;
import tuti.desi.servicios.ContratoService;
import tuti.desi.servicios.PropiedadService;
import tuti.desi.servicios.PersonaService; 

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/contratos")
public class ContratoController {

    @Autowired
    private ContratoService contratoService;

    @Autowired
    private PropiedadService propiedadService;
    @Autowired
    private PersonaService personaService;
    

    // Listado de contratos con filtros dinámicos opcionales.
    @GetMapping
    public String listarContratos(
            @RequestParam(value = "propiedadId", required = false) Long propiedadId,
            @RequestParam(value = "inquilinoId", required = false) Long inquilinoId,
            @RequestParam(value = "estado", required = false) EstadoContrato estado,
            @RequestParam(value = "fechaInicio", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            Model model) {

        List<Contrato> listaContratos = contratoService.buscarConFiltros(propiedadId, inquilinoId, estado, fechaInicio);
        
        model.addAttribute("contratos", listaContratos);
        model.addAttribute("estados", EstadoContrato.values());
        
        // listas para los desplegables de búsqueda en la vista
        model.addAttribute("propiedades", propiedadService.listarDisponiblesParaPublicar());
        model.addAttribute("inquilinos", personaService.listarTodasActivas());

        // Retorna la vista HTML: /templates/contratos/lista.html
        return "contratos/lista"; 
    }

    // Formulario alta para un NUEVO contrato.
    @GetMapping("/nuevo")
    public String mostrarFormularioAlta(Model model) {
        Contrato nuevoContrato = new Contrato();
        // Seteamos estado inicial por defecto para el formulario: BORRADOR
        nuevoContrato.setEstado(EstadoContrato.BORRADOR); 
        
        model.addAttribute("contrato", nuevoContrato);
        model.addAttribute("estados", EstadoContrato.values());
        model.addAttribute("propiedades", propiedadService.listarDisponiblesParaPublicar());
        model.addAttribute("inquilinos", personaService.listarTodasActivas());

        return "contratos/formulario";
    }

    // Procesar el formulario de alta.
    @PostMapping("/nuevo")
    public String guardarNuevoContrato(@ModelAttribute("contrato") Contrato contrato, 
                                    RedirectAttributes redirectAttributes) {
        try {
            contratoService.crearContrato(contrato);
            redirectAttributes.addFlashAttribute("mensajeExito", "¡Contrato registrado con éxito!");
            return "redirect:/contratos";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensajeError", "Error al crear contrato: " + e.getMessage());
            return "redirect:/contratos/nuevo";
        }
    }

    // Formulario de MODIFICACIÓN de un contrato existente.
    @GetMapping("/editar/{id}")
    public String mostrarFormularioModificacion(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Contrato contratoExistente = contratoService.buscarPorId(id);
            model.addAttribute("contrato", contratoExistente);
            model.addAttribute("estados", EstadoContrato.values());
            model.addAttribute("propiedades", propiedadService.listarDisponiblesParaPublicar());
            model.addAttribute("inquilinos", personaService.listarTodasActivas());
            return "contratos/formulario";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensajeError", "No se pudo editar: " + e.getMessage());
            return "redirect:/contratos";
        }
    }

    // Procesar la modificación del contrato.
    @PostMapping("/editar/{id}")
    public String actualizarContrato(@PathVariable("id") Long id, 
                                    @ModelAttribute("contrato") Contrato contrato, 
                                    RedirectAttributes redirectAttributes) {
        try {
            contrato.setId(id); // Forzamos el id de la URL, para evitar manipulaciones
            contratoService.modificarContrato(contrato);
            redirectAttributes.addFlashAttribute("mensajeExito", "¡Contrato modificado correctamente!");
            return "redirect:/contratos";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensajeError", "Error al modificar: " + e.getMessage());
            return "redirect:/contratos/editar/" + id;
        }
    }

    // ELIMINACIÓN lógica de un contrato (solo para estado = BORRADOR)
    @GetMapping("/eliminar/{id}")
    public String eliminarContrato(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            contratoService.eliminarContrato(id);
            redirectAttributes.addFlashAttribute("mensajeExito", "El contrato se eliminó de forma correcta.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensajeError", "No se puede eliminar: " + e.getMessage());
        }
        return "redirect:/contratos";
    }
}