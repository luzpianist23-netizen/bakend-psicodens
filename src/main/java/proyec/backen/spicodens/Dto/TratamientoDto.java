package proyec.backen.spicodens.Dto;


import java.time.LocalDate;

public class TratamientoDto {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Boolean estado;
    private String observaciones;

    private Long profesionalId;
    private Long pacienteId;
    private Long servicioOdontologicoId;
    private Long servicioPsicologicoId;     // opcional si lo manejas también
    private Long historialOdontologicoId;   // opcional

    // ===== Getters y Setters =====

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Long getProfesionalId() {
        return profesionalId;
    }

    public void setProfesionalId(Long profesionalId) {
        this.profesionalId = profesionalId;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Long getServicioOdontologicoId() {
        return servicioOdontologicoId;
    }

    public void setServicioOdontologicoId(Long servicioOdontologicoId) {
        this.servicioOdontologicoId = servicioOdontologicoId;
    }

    public Long getServicioPsicologicoId() {
        return servicioPsicologicoId;
    }

    public void setServicioPsicologicoId(Long servicioPsicologicoId) {
        this.servicioPsicologicoId = servicioPsicologicoId;
    }

    public Long getHistorialOdontologicoId() {
        return historialOdontologicoId;
    }

    public void setHistorialOdontologicoId(Long historialOdontologicoId) {
        this.historialOdontologicoId = historialOdontologicoId;
    }
}
