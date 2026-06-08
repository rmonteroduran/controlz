package tuti.desi.presentacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tuti.desi.entidades.Persona;
import tuti.desi.servicios.PersonaService;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    // 1. FORMULARIO DE ALTA
    @GetMapping("/nueva")
    public String formularioAlta(Model model) {
        model.addAttribute("persona", new Persona());
        return "personas/formulario";
    }

    // 2. GUARDADO
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Persona persona) {
        personaService.guardar(persona);
        return "redirect:/propiedades/nueva";
    }

    // 3. API PARA ALTA RÁPIDA (MODAL)
    @PostMapping("/api/guardar")
    @ResponseBody
    public ResponseEntity<?> guardarAjax(@RequestBody Persona persona) {
        if (persona.getNombre() == null || persona.getNombre().trim().isEmpty() ||
            persona.getApellido() == null || persona.getApellido().trim().isEmpty() ||
            persona.getDniCuit() == null || persona.getDniCuit().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Los campos Nombre, Apellido y DNI/CUIT son obligatorios.");
        }
        
        personaService.guardar(persona);
        
        Map<String, Object> response = new HashMap<>();
        response.put("id", persona.getId());
        response.put("nombreCompleto", persona.getNombreCompleto());
        return ResponseEntity.ok(response);
    }
}
