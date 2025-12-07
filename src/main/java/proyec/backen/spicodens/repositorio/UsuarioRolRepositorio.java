package proyec.backen.spicodens.repositorio;
// UsuarioRolRepositorio.java



import org.springframework.data.jpa.repository.JpaRepository;

import proyec.backen.spicodens.modelo.UsuarioRol;
import proyec.backen.spicodens.modelo.UsuarioRolId;

import java.util.List;
import java.util.Optional;

public interface UsuarioRolRepositorio extends JpaRepository<UsuarioRol, UsuarioRolId> {
    List<UsuarioRol> findByIdUsuario(Long idUsuario);
    Optional<UsuarioRol> findByIdUsuarioAndIdRol(Long idUsuario, Long idRol);
}
