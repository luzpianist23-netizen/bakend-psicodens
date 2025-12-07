package proyec.backen.spicodens.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import proyec.backen.spicodens.modelo.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    
    //  Buscar usuario por nombre de usuario (para login o validaciones)
    Optional<Usuario> findByUsername(String username);

    //  Verificar si ya existe un username (para validaciones de registro)
    boolean existsByUsername(String username);

    // Obtener todos los usuarios activos con su persona asociada
    @Query("""
        SELECT u FROM Usuario u
        JOIN FETCH u.persona p
        WHERE u.activo = true
        ORDER BY p.apellidoPaterno, p.nombre
    """)
    List<Usuario> findAllActiveWithPersona();

    
}
