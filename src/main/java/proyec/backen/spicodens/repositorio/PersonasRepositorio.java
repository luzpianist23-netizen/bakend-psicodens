package proyec.backen.spicodens.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import proyec.backen.spicodens.modelo.Persona;
import java.util.List;

public interface PersonasRepositorio extends JpaRepository<Persona, Long> {

    @Query(
        value = "SELECT p.* " +
                "FROM persona p " +
                "LEFT JOIN paciente pa ON p.id = pa.persona_id " +
                "LEFT JOIN usuario u ON p.id = u.id_persona " +
                "WHERE pa.id IS NULL " +
                "AND u.id IS NULL " +
                "AND p.activo = true",
        nativeQuery = true
    )
    List<Persona> findPersonasSinPacienteNiUsuarioActivas();
}