package tuti.desi.presentacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tuti.desi.accesoDatos.IContratoRepo;
import tuti.desi.entidades.Factura;
import tuti.desi.entidades.EstadoFactura;
import tuti.desi.entidades.MedioDePago;
import tuti.desi.servicios.FacturaService;
import java.util.List;

@Controller
@RequestMapping("/facturas")
public class FacturaController {
	
	@Autowired
    private FacturaService facturaService;

    @Autowired
    private IContratoRepo contratoRepo;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("facturas", facturaService.listarActivas());
        return "facturas/lista";
    }

    @GetMapping("/nueva")
    public String formularioAlta(Model model) {
        model.addAttribute("factura", new Factura());
        model.addAttribute("contratos", contratoRepo.findAll());
        model.addAttribute("estados", EstadoFactura.values());
        model.addAttribute("mediosDePago", MedioDePago.values());
        return "facturas/formulario";
    }

    @PostMapping("/nueva")
    public String guardarAlta(@ModelAttribute Factura factura,
                               RedirectAttributes redirectAttrs) {
        try {
            facturaService.crear(factura);
            redirectAttrs.addFlashAttribute("exito", "Factura creada correctamente.");
        } catch (IllegalArgumentException e) {
            redirectAttrs.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/facturas";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model,
                                    RedirectAttributes redirectAttrs) {
        try {
            Factura factura = facturaService.buscarPorId(id);
            model.addAttribute("factura", factura);
            model.addAttribute("contratos", contratoRepo.findAll());
            model.addAttribute("estados", EstadoFactura.values());
            model.addAttribute("mediosDePago", MedioDePago.values());
            return "facturas/formulario";
        } catch (IllegalArgumentException e) {
            redirectAttrs.addFlashAttribute("error", e.getMessage());
            return "redirect:/facturas";
        }
    }

    @PostMapping("/editar/{id}")
    public String guardarEdicion(@PathVariable Long id,
                                  @ModelAttribute Factura factura,
                                  RedirectAttributes redirectAttrs) {
        try {
            factura.setId(id);
            facturaService.modificar(factura);
            redirectAttrs.addFlashAttribute("exito", "Factura modificada correctamente.");
        } catch (IllegalArgumentException e) {
            redirectAttrs.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/facturas";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttrs) {
        try {
            facturaService.eliminar(id);
            redirectAttrs.addFlashAttribute("exito", "Factura eliminada correctamente.");
        } catch (IllegalArgumentException e) {
            redirectAttrs.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/facturas";
    }

}
