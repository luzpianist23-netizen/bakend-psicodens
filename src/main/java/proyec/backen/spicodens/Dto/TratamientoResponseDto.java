package proyec.backen.spicodens.Dto;

import java.time.LocalDate;
import proyec.backen.spicodens.modelo.Paciente;
import proyec.backen.spicodens.modelo.Profesional;
import proyec.backen.spicodens.modelo.ServicioOdontologico;
import proyec.backen.spicodens.modelo.ServicioPsicologico;

public class TratamientoResponseDto {
    private Long id;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Boolean estado;
    private String observaciones;
    private Profesional profesional;
    private Paciente paciente;
    private ServicioOdontologico servicioOdontologico;
    private ServicioPsicologico servicioPsicologico;

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

    public Profesional getProfesional() { return profesional; }
    public void setProfesional(Profesional profesional) { this.profesional = profesional; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public ServicioOdontologico getServicioOdontologico() { return servicioOdontologico; }
    public void setServicioOdontologico(ServicioOdontologico servicioOdontologico) { this.servicioOdontologico = servicioOdontologico; }

    public ServicioPsicologico getServicioPsicologico() { return servicioPsicologico; }
    public void setServicioPsicologico(ServicioPsicologico servicioPsicologico) { this.servicioPsicologico = servicioPsicologico; }
}
