package proyec.backen.spicodens.controlador;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import proyec.backen.spicodens.Dto.CitaDto;
import proyec.backen.spicodens.modelo.Cita;
import proyec.backen.spicodens.repositorio.CitaRepositorio;
import proyec.backen.spicodens.repositorio.PacienteRepositorio;
import proyec.backen.spicodens.repositorio.ProfesionalRepositorio;
import proyec.backen.spicodens.repositorio.RecepcionistaRepositorio;
import proyec.backen.spicodens.repositorio.TratamientoRepositorio;
import proyec.backen.spicodens.servicios.CitaServicio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin("*")
public class CitaControlador {

    @Autowired
    private CitaRepositorio citaRepositorio;

    @Autowired
    private PacienteRepositorio pacienteRepositorio;

    @Autowired
    private ProfesionalRepositorio profesionalRepositorio;

    @Autowired
    private RecepcionistaRepositorio recepcionistaRepositorio;

    @Autowired
    private TratamientoRepositorio tratamientoRepositorio;

    @Autowired
    private CitaServicio citaServicio;



    private CitaDto mapToDto(Cita c) {
        return new CitaDto(
                c.getId(),
                c.getFecha(),
                c.getHora(),
                c.getEstado(),
                c.getPaciente() != null ? c.getPaciente().getId() : null,
                c.getPersona() != null ? c.getPersona().getId() : null,
                c.getProfesional() != null ? c.getProfesional().getId() : null,
                c.getRecepcionista() != null ? c.getRecepcionista().getId() : null,
                c.getTratamiento() != null ? c.getTratamiento().getId() : null);
    }

    private Cita mapToEntity(CitaDto dto, Cita c) {

        c.setFecha(dto.getFecha());
        c.setHora(dto.getHora());
        c.setEstado(dto.getEstado());

        if (dto.getPacienteId() != null) {
            pacienteRepositorio.findById(dto.getPacienteId())
                    .ifPresent(c::setPaciente);
        }

        if (dto.getPersonaId() != null) {
            // setPersona() según tu entity
            // si no existe, debes implementarlo
        }

        if (dto.getProfesionalId() != null) {
            profesionalRepositorio.findById(dto.getProfesionalId())
                    .ifPresent(c::setProfesional);
        }

        if (dto.getRecepcionistaId() != null) {
            recepcionistaRepositorio.findById(dto.getRecepcionistaId())
                    .ifPresent(c::setRecepcionista);
        }

        if (dto.getTratamientoId() != null) {
            tratamientoRepositorio.findById(dto.getTratamientoId())
                    .ifPresent(c::setTratamiento);
        }

        return c;
    }

    @GetMapping
    public ResponseEntity<List<CitaDto>> listar() {
        List<CitaDto> lista = citaRepositorio.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaDto> obtenerPorId(@PathVariable Long id) {
        return citaRepositorio.findById(id)
                .map(c -> ResponseEntity.ok(mapToDto(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<List<CitaDto>> listarPorPaciente(@PathVariable Long idPaciente) {
        List<CitaDto> lista = citaRepositorio.findByPacienteId(idPaciente)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<CitaDto> registrar(@RequestBody CitaDto dto) {
        Cita c = new Cita();
        mapToEntity(dto, c);
        c = citaRepositorio.save(c);
        return ResponseEntity.ok(mapToDto(c));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaDto> modificar(@PathVariable Long id, @RequestBody CitaDto dto) {
        return citaRepositorio.findById(id)
                .map(c -> {
                    mapToEntity(dto, c);
                    c = citaRepositorio.save(c);
                    return ResponseEntity.ok(mapToDto(c));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/cancelar/{id}")
    public ResponseEntity<CitaDto> cancelar(@PathVariable Long id) {
        return citaRepositorio.findById(id)
                .map(c -> {
                    c.setEstado("CANCELADA");
                    c = citaRepositorio.save(c);
                    return ResponseEntity.ok(mapToDto(c));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        citaRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<LocalTime>> obtenerDisponibles(
            @RequestParam Long profesionalId,
            @RequestParam String fecha) {
        LocalDate f = LocalDate.parse(fecha);
        return ResponseEntity.ok(
                citaServicio.obtenerHorasDisponibles(profesionalId, f));
    }
}
