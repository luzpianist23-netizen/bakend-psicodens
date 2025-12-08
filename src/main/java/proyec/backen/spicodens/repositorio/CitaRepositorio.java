package proyec.backen.spicodens.repositorio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import proyec.backen.spicodens.modelo.Cita;

public interface CitaRepositorio extends JpaRepository<Cita, Long> {

    
        List<Cita> findByPacienteId(Long idPaciente);

        boolean existsByProfesionalIdAndFechaAndHora(Long profesionalId, LocalDate fecha, LocalTime hora);

        @Query("SELECT c.hora FROM Cita c " +
                        "WHERE c.profesional.id = :profesionalId " +
                        "AND c.fecha = :fecha")
        List<LocalTime> findHorasOcupadas(Long profesionalId, LocalDate fecha);
}
