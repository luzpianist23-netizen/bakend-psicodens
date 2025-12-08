package proyec.backen.spicodens.Dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class CitaResponseDto {
    private Long id;
    private LocalDate fecha;
    private LocalTime hora;
    private String estado;

    private PersonaDto persona;       // solo si no es paciente
    private PacienteDto paciente;     // si ya está registrado
    private TratamientoResponseDto tratamiento; // opcional
    private ProfesionalDto profesional;
    private RecepcionistaDto recepcionista;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public PersonaDto getPersona() { return persona; }
    public void setPersona(PersonaDto persona) { this.persona = persona; }

    public PacienteDto getPaciente() { return paciente; }
    public void setPaciente(PacienteDto paciente) { this.paciente = paciente; }

    public TratamientoResponseDto getTratamiento() { return tratamiento; }
    public void setTratamiento(TratamientoResponseDto tratamiento) { this.tratamiento = tratamiento; }

    public ProfesionalDto getProfesional() { return profesional; }
    public void setProfesional(ProfesionalDto profesional) { this.profesional = profesional; }

    public RecepcionistaDto getRecepcionista() { return recepcionista; }
    public void setRecepcionista(RecepcionistaDto recepcionista) { this.recepcionista = recepcionista; }
}
