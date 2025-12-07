package proyec.backen.spicodens.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import proyec.backen.spicodens.modelo.Recepcionista;
import proyec.backen.spicodens.modelo.Usuario;

public interface RecepcionistaRepositorio extends JpaRepository<Recepcionista, Long> {
    Optional<Recepcionista> findByUsuario(Usuario usuario);
}