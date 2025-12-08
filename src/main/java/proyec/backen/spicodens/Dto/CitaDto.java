package proyec.backen.spicodens.Dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CitaDto {

    private Long id;
    private LocalDate fecha;
    private LocalTime hora;
    private String estado;

    // Relaciones por ID (evitan ciclos al serializar/deserializar)
    private Long pacienteId;      // si la cita es para un paciente registrado
    private Long personaId;       // si es primera cita (persona no registrada como paciente)
    private Long profesionalId;   // obligatorio en POST/PUT
    private Long recepcionistaId; // obligatorio en POST/PUT
    private Long tratamientoId;   // opcional

    public CitaDto() {}

    public CitaDto(Long id, LocalDate fecha, LocalTime hora, String estado,
                   Long pacienteId, Long personaId, Long profesionalId,
                   Long recepcionistaId, Long tratamientoId) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.pacienteId = pacienteId;
        this.personaId = personaId;
        this.profesionalId = profesionalId;
        this.recepcionistaId = recepcionistaId;
        this.tratamientoId = tratamientoId;
    }

    // ===== Getters & Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Long getPacienteId() { return pacienteId; }
    public void setPacienteId(Long pacienteId) { this.pacienteId = pacienteId; }

    public Long getPersonaId() { return personaId; }
    public void setPersonaId(Long personaId) { this.personaId = personaId; }

    public Long getProfesionalId() { return profesionalId; }
    public void setProfesionalId(Long profesionalId) { this.profesionalId = profesionalId; }

    public Long getRecepcionistaId() { return recepcionistaId; }
    public void setRecepcionistaId(Long recepcionistaId) { this.recepcionistaId = recepcionistaId; }

    public Long getTratamientoId() { return tratamientoId; }
    public void setTratamientoId(Long tratamientoId) { this.tratamientoId = tratamientoId; }
}
