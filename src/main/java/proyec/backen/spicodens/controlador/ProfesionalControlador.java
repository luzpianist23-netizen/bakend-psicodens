package proyec.backen.spicodens.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import proyec.backen.spicodens.modelo.Profesional;
import proyec.backen.spicodens.repositorio.ProfesionalRepositorio;
import proyec.backen.spicodens.repositorio.UsuarioRepositorio;

import java.util.List;

@RestController
@RequestMapping("/api/profesionales")
public class ProfesionalControlador {

    @Autowired
    private ProfesionalRepositorio profesionalRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    // ✅ LISTAR PROFESIONALES
    @GetMapping
    public List<Profesional> listarProfesionales() {
        return profesionalRepositorio.findAll(Sort.by("id"));
    }

    // ✅ REGISTRAR PROFESIONAL
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarProfesional(@RequestBody Profesional profesional) {

        if (profesional.getUsuario() == null || profesional.getUsuario().getId() == null) {
            return ResponseEntity.badRequest().body("Debe enviar un usuario válido.");
        }

        var usuarioOpt = usuarioRepositorio.findById(profesional.getUsuario().getId());
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("El usuario no existe.");
        }

        if (profesionalRepositorio.existsByUsuarioId(usuarioOpt.get().getId())) {
            return ResponseEntity.badRequest().body("Este usuario ya está registrado como profesional.");
        }

        if (profesionalRepositorio.existsByMatriculaProfesional(profesional.getMatriculaProfesional())) {
            return ResponseEntity.badRequest().body("La matrícula profesional ya está registrada.");
        }

        profesional.setUsuario(usuarioOpt.get());
        profesional.setActivo(true);

        Profesional profesionalGuardado = profesionalRepositorio.save(profesional);

        return ResponseEntity.ok(profesionalGuardado);
    }
}
