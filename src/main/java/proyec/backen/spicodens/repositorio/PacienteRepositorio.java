package proyec.backen.spicodens.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import proyec.backen.spicodens.modelo.Paciente;



public interface PacienteRepositorio extends JpaRepository<Paciente, Long> {

}
