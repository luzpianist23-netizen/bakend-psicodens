package proyec.backen.spicodens.modelo;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.time.LocalDate;

@Entity
@Table(name = "tratamiento")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Tratamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "estado", nullable = false)
    private Boolean estado = true;

    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;

    // 🔹 Relaciones
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_profesional", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Profesional profesional;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_paciente", referencedColumnName = "id", nullable = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_servicio_odontologico")
    private ServicioOdontologico servicioOdontologico;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_servicio_psicologico")
    private ServicioPsicologico servicioPsicologico;


    // ====== GETTERS Y SETTERS ======

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Profesional getProfesional() {
        return profesional;
    }

    public void setProfesional(Profesional profesional) {
        this.profesional = profesional;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public ServicioOdontologico getServicioOdontologico() {
        return servicioOdontologico;
    }

    public void setServicioOdontologico(ServicioOdontologico servicioOdontologico) {
        this.servicioOdontologico = servicioOdontologico;
    }

    public ServicioPsicologico getServicioPsicologico() {
        return servicioPsicologico;
    }

    public void setServicioPsicologico(ServicioPsicologico servicioPsicologico) {
        this.servicioPsicologico = servicioPsicologico;
    }
}
