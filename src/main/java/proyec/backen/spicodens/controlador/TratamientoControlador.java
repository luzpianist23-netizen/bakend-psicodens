package proyec.backen.spicodens.controlador;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import proyec.backen.spicodens.Dto.TratamientoDto;

import proyec.backen.spicodens.modelo.Paciente;
import proyec.backen.spicodens.modelo.Profesional;
import proyec.backen.spicodens.modelo.Tratamiento;
import proyec.backen.spicodens.repositorio.PacienteRepositorio;
import proyec.backen.spicodens.repositorio.ProfesionalRepositorio;
import proyec.backen.spicodens.repositorio.ServicioOdontologicoRepositorio;
import proyec.backen.spicodens.repositorio.ServicioPsicologicoRepositorio;
import proyec.backen.spicodens.repositorio.TratamientoRepositorio;

import java.util.List;


@RestController
@RequestMapping("/api/tratamientos")

public class TratamientoControlador {

    @Autowired
    private TratamientoRepositorio tratamientoRepositorio;

    @Autowired
    private PacienteRepositorio pacienteRepositorio;

    @Autowired
    private ProfesionalRepositorio profesionalRepositorio;

    @Autowired
    private ServicioOdontologicoRepositorio servicioOdontologicoRepositorio;

    @Autowired
    private ServicioPsicologicoRepositorio servicioPsicologicoRepositorio;

    // --- LISTAR ---
    @GetMapping
     public List<Tratamiento> listarTratamientos() {
        return tratamientoRepositorio.findAll();
     }

    @PostMapping("/directo")
public ResponseEntity<?> registrarTratamientoDirecto(@RequestBody Tratamiento tratamiento) {
    try {
        if(tratamiento.getProfesional() == null || tratamiento.getPaciente() == null) {
            return ResponseEntity.badRequest().body("Profesional y Paciente son obligatorios");
        }

        // Guardar directamente
        Tratamiento saved = tratamientoRepositorio.save(tratamiento);
        return ResponseEntity.ok(saved);

    } catch (Exception e) {
        return ResponseEntity.badRequest().body("Error: " + e.getMessage());
    }
}


    // --- REGISTRAR ---
    @PostMapping
    public ResponseEntity<?> registrarTratamiento(@RequestBody TratamientoDto dto) {
        try {
            Tratamiento tratamiento = new Tratamiento();
            tratamiento.setFechaInicio(dto.getFechaInicio());
            tratamiento.setFechaFin(dto.getFechaFin());
            tratamiento.setEstado(dto.getEstado());
            tratamiento.setObservaciones(dto.getObservaciones());

            // Relaciones
            Paciente paciente = pacienteRepositorio.findById(dto.getPacienteId())
                    .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
            tratamiento.setPaciente(paciente);

            Profesional profesional = profesionalRepositorio.findById(dto.getProfesionalId())
                    .orElseThrow(() -> new RuntimeException("Profesional no encontrado"));
            tratamiento.setProfesional(profesional);

            if (dto.getServicioOdontologicoId() != null) {
                tratamiento.setServicioOdontologico(servicioOdontologicoRepositorio
                        .findById(dto.getServicioOdontologicoId())
                        .orElse(null));
            }

            if (dto.getServicioPsicologicoId() != null) {
                tratamiento.setServicioPsicologico(servicioPsicologicoRepositorio
                        .findById(dto.getServicioPsicologicoId())
                        .orElse(null));
            }

            Tratamiento saved = tratamientoRepositorio.save(tratamiento);
            return ResponseEntity.ok(saved);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }


    // --- CULMINAR TRATAMIENTO ---
@PutMapping("/{id}/culminar")
public ResponseEntity<?> culminarTratamiento(@PathVariable Long id) {
    return tratamientoRepositorio.findById(id).map(tratamiento -> {
        if (Boolean.FALSE.equals(tratamiento.getEstado())) {
            return ResponseEntity.badRequest().body("El tratamiento ya está culminado.");
        }

        tratamiento.setEstado(false);
        tratamiento.setFechaFin(java.time.LocalDate.now());

        Tratamiento actualizado = tratamientoRepositorio.save(tratamiento);
        return ResponseEntity.ok(actualizado);
    }).orElse(ResponseEntity.notFound().build());
}

}
