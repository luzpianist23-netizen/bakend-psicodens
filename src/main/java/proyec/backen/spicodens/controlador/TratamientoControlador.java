package proyec.backen.spicodens.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import proyec.backen.spicodens.Dto.TratamientoDto;
import proyec.backen.spicodens.Dto.TratamientoResponseDto;
import proyec.backen.spicodens.Dto.UsuarioDto;
import proyec.backen.spicodens.Dto.PacienteDto;
import proyec.backen.spicodens.Dto.PersonaDto;
import proyec.backen.spicodens.Dto.ProfesionalDto;
import proyec.backen.spicodens.Dto.ServicioOdontologicoDto;
import proyec.backen.spicodens.Dto.ServicioPsicologicoDto;

import proyec.backen.spicodens.modelo.Tratamiento;
import proyec.backen.spicodens.modelo.Paciente;
import proyec.backen.spicodens.modelo.Profesional;

import proyec.backen.spicodens.repositorio.TratamientoRepositorio;
import proyec.backen.spicodens.repositorio.PacienteRepositorio;
import proyec.backen.spicodens.repositorio.ProfesionalRepositorio;
import proyec.backen.spicodens.repositorio.ServicioOdontologicoRepositorio;
import proyec.backen.spicodens.repositorio.ServicioPsicologicoRepositorio;

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

    // ===== Listar tratamientos =====
   @GetMapping
public List<TratamientoResponseDto> listarTratamientos() {
    return tratamientoRepositorio.findAll().stream().map(tratamiento -> {
        TratamientoResponseDto dto = new TratamientoResponseDto();
        dto.setId(tratamiento.getId());
        dto.setFechaInicio(tratamiento.getFechaInicio());
        dto.setFechaFin(tratamiento.getFechaFin());
        dto.setEstado(tratamiento.getEstado());
        dto.setObservaciones(tratamiento.getObservaciones());

        // Mapear Profesional
        ProfesionalDto profDto = new ProfesionalDto();
        profDto.setId(tratamiento.getProfesional().getId());
        profDto.setMatriculaProfesional(tratamiento.getProfesional().getMatriculaProfesional());
        profDto.setDescripcion(tratamiento.getProfesional().getDescripcion());
        profDto.setActivo(tratamiento.getProfesional().getActivo());
        if(tratamiento.getProfesional().getUsuario() != null) {
            UsuarioDto userDto = new UsuarioDto();
            userDto.setId(tratamiento.getProfesional().getUsuario().getId());
            userDto.setUsername(tratamiento.getProfesional().getUsuario().getUsername());
            if(tratamiento.getProfesional().getUsuario().getPersona() != null) {
                PersonaDto personaDto = new PersonaDto();
                personaDto.setId(tratamiento.getProfesional().getUsuario().getPersona().getId());
                personaDto.setNombre(tratamiento.getProfesional().getUsuario().getPersona().getNombre());
                personaDto.setApellidoPaterno(tratamiento.getProfesional().getUsuario().getPersona().getApellidoPaterno());
                personaDto.setApellidoMaterno(tratamiento.getProfesional().getUsuario().getPersona().getApellidoMaterno());
                personaDto.setCedula(tratamiento.getProfesional().getUsuario().getPersona().getCedula());
                personaDto.setEmail(tratamiento.getProfesional().getUsuario().getPersona().getEmail());
                userDto.setPersona(personaDto);
            }
            profDto.setUsuario(userDto);
        }
        dto.setProfesional(profDto);

        // Mapear Paciente
        PacienteDto pacienteDto = new PacienteDto();
        pacienteDto.setId(tratamiento.getPaciente().getId());
        pacienteDto.setTipoPaciente(tratamiento.getPaciente().getTipoPaciente());
        if(tratamiento.getPaciente().getPersona() != null) {
            PersonaDto personaDto = new PersonaDto();
            personaDto.setId(tratamiento.getPaciente().getPersona().getId());
            personaDto.setNombre(tratamiento.getPaciente().getPersona().getNombre());
            personaDto.setApellidoPaterno(tratamiento.getPaciente().getPersona().getApellidoPaterno());
            personaDto.setApellidoMaterno(tratamiento.getPaciente().getPersona().getApellidoMaterno());
            personaDto.setCedula(tratamiento.getPaciente().getPersona().getCedula());
            personaDto.setEmail(tratamiento.getPaciente().getPersona().getEmail());
            pacienteDto.setPersona(personaDto);
        }
        dto.setPaciente(pacienteDto);

        // Mapear servicios
        if(tratamiento.getServicioOdontologico() != null) {
            ServicioOdontologicoDto odontoDto = new ServicioOdontologicoDto();
            odontoDto.setId(tratamiento.getServicioOdontologico().getId());
            odontoDto.setNombre(tratamiento.getServicioOdontologico().getNombre());
            odontoDto.setPrecio(tratamiento.getServicioOdontologico().getPrecio());
            dto.setServicioOdontologico(odontoDto);
        }

        if(tratamiento.getServicioPsicologico() != null) {
            ServicioPsicologicoDto psiDto = new ServicioPsicologicoDto();
            psiDto.setId(tratamiento.getServicioPsicologico().getId());
            psiDto.setNombre(tratamiento.getServicioPsicologico().getNombre());
            psiDto.setPrecio(tratamiento.getServicioPsicologico().getPrecio());
            dto.setServicioPsicologico(psiDto);
        }

        return dto;
    }).toList();
}


    // ===== Registrar tratamiento =====
    @PostMapping
    public ResponseEntity<?> registrarTratamiento(@RequestBody TratamientoDto dto) {
        try {
            Tratamiento tratamiento = new Tratamiento();
            tratamiento.setFechaInicio(dto.getFechaInicio());
            tratamiento.setFechaFin(dto.getFechaFin());
            tratamiento.setEstado(dto.getEstado());
            tratamiento.setObservaciones(dto.getObservaciones());

            Paciente paciente = pacienteRepositorio.findById(dto.getPacienteId())
                    .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
            tratamiento.setPaciente(paciente);

            Profesional profesional = profesionalRepositorio.findById(dto.getProfesionalId())
                    .orElseThrow(() -> new RuntimeException("Profesional no encontrado"));
            tratamiento.setProfesional(profesional);

            if (dto.getServicioOdontologicoId() != null) {
                tratamiento.setServicioOdontologico(
                        servicioOdontologicoRepositorio.findById(dto.getServicioOdontologicoId()).orElse(null));
            }

            if (dto.getServicioPsicologicoId() != null) {
                tratamiento.setServicioPsicologico(
                        servicioPsicologicoRepositorio.findById(dto.getServicioPsicologicoId()).orElse(null));
            }

            Tratamiento saved = tratamientoRepositorio.save(tratamiento);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    // ===== Culminar tratamiento =====
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
