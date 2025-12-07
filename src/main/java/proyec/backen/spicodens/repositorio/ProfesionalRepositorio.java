package proyec.backen.spicodens.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import proyec.backen.spicodens.modelo.Profesional;
import proyec.backen.spicodens.modelo.Usuario;


public interface ProfesionalRepositorio extends JpaRepository<Profesional, Long>{
    boolean existsByUsuarioId(Long usuarioId);
boolean existsByMatriculaProfesional(String matriculaProfesional);

Optional<Profesional> findByUsuario(Usuario usuario);
}
