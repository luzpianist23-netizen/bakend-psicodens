package proyec.backen.spicodens.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import proyec.backen.spicodens.modelo.Profesional;
import proyec.backen.spicodens.modelo.Usuario;


public interface ProfesionalRepositorio extends JpaRepository<Profesional, Long>{
Optional<Profesional> findByUsuario(Usuario usuario);
}
