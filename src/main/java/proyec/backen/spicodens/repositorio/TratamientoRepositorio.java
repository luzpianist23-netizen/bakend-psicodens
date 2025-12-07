package proyec.backen.spicodens.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import proyec.backen.spicodens.modelo.Tratamiento;



public interface TratamientoRepositorio extends JpaRepository<Tratamiento, Long> {

    @Query("SELECT t FROM Tratamiento t " +
       "JOIN FETCH t.paciente p " +
       "JOIN FETCH p.persona per " +
       "LEFT JOIN FETCH t.servicioOdontologico so " +
       "LEFT JOIN FETCH t.servicioPsicologico sp")
List<Tratamiento> findAllConServiciosYPaciente();

}
