    package proyec.backen.spicodens.controlador;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.data.domain.Sort;

    import org.springframework.web.bind.annotation.*;

    import proyec.backen.spicodens.modelo.Paciente;
    import proyec.backen.spicodens.repositorio.PacienteRepositorio;

    import java.util.List;

    @RestController
    @RequestMapping("/api/pacientes") 
    public class PacienteControlador {

        @Autowired
        private PacienteRepositorio pacienteRepositorio;

        @GetMapping
        public List<Paciente> listarPacientes() {
            return pacienteRepositorio.findAll(Sort.by("id"));
        }
        @PostMapping
        public Paciente registrarPaciente(@RequestBody Paciente paciente) {
            return pacienteRepositorio.save(paciente);
        }
        
    }