package proyec.backen.spicodens.Dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ConsultaInicialRequestDto {

    private LocalDate fecha;
    private LocalTime hora;

    private Long personaId;        // se registra una nueva persona

    private Long profesionalId;        // ya existen
    private Long recepcionistaId;
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public LocalTime getHora() {
        return hora;
    }
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
    public Long getPersonaId() {
        return personaId;
    }
    public void setPersonaId(Long personaId) {
        this.personaId = personaId;
    }
    public Long getProfesionalId() {
        return profesionalId;
    }
    public void setProfesionalId(Long profesionalId) {
        this.profesionalId = profesionalId;
    }
    public Long getRecepcionistaId() {
        return recepcionistaId;
    }
    public void setRecepcionistaId(Long recepcionistaId) {
        this.recepcionistaId = recepcionistaId;
    }
    
    
}