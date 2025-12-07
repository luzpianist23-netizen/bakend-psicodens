package proyec.backen.spicodens.Dto;

import java.time.LocalDate;

public class TratamientoResponseDto {
    private Long id;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Boolean estado;
    private String observaciones;

    private ProfesionalDto profesional;
    private PacienteDto paciente;
    private ServicioOdontologicoDto servicioOdontologico;
    private ServicioPsicologicoDto servicioPsicologico;

    // ===== Getters y Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }

    public Boolean getEstado() { return estado; }
    public void setEstado(Boolean estado) { this.estado = estado; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public ProfesionalDto getProfesional() { return profesional; }
    public void setProfesional(ProfesionalDto profesional) { this.profesional = profesional; }

    public PacienteDto getPaciente() { return paciente; }
    public void setPaciente(PacienteDto paciente) { this.paciente = paciente; }

    public ServicioOdontologicoDto getServicioOdontologico() { return servicioOdontologico; }
    public void setServicioOdontologico(ServicioOdontologicoDto servicioOdontologico) { this.servicioOdontologico = servicioOdontologico; }

    public ServicioPsicologicoDto getServicioPsicologico() { return servicioPsicologico; }
    public void setServicioPsicologico(ServicioPsicologicoDto servicioPsicologico) { this.servicioPsicologico = servicioPsicologico; }
}
