package proyec.backen.spicodens.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import proyec.backen.spicodens.modelo.Persona;
import proyec.backen.spicodens.repositorio.PersonasRepositorio; 

@RestController
@RequestMapping("/api/personas")
public class PersonasControlador {

    @Autowired
    private PersonasRepositorio personaRepositorio;

    // Listar todas las personas ordenadas por ID
    @GetMapping
    public List<Persona> listarPersonas() {
        return personaRepositorio.findAll(Sort.by("id"));
    }

    
    // Registrar nueva persona
    @PostMapping
    public Persona registrarPersona(@RequestBody Persona persona) {
        return personaRepositorio.save(persona);
    }



    // Listar personas activas que no son pacientes ni tienen usuario
    @GetMapping("/libres")
    public List<Persona> listarPersonasLibres() {
        return personaRepositorio.findPersonasSinPacienteNiUsuarioActivas();
    }
}
