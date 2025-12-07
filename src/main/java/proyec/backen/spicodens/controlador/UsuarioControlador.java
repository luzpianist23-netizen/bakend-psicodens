package proyec.backen.spicodens.controlador;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import proyec.backen.spicodens.modelo.Usuario;
import proyec.backen.spicodens.repositorio.PersonasRepositorio;
import proyec.backen.spicodens.repositorio.UsuarioRepositorio;

public class UsuarioControlador {
    
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private PersonasRepositorio personaRepositorio;

      @GetMapping
    public List<Usuario> listarUsuariosActivos() {
        return usuarioRepositorio.findAllActiveWithPersona();
    }
 @PostMapping("/api/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario) {

       
        if (usuario.getPersona() == null || usuario.getPersona().getId() == null) {
            return ResponseEntity.badRequest().body("Debe enviar una persona válida con id_persona.");
        }

        var personaOpt = personaRepositorio.findById(usuario.getPersona().getId());
        if (personaOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("La persona no existe en la base de datos.");
        }

        if (usuarioRepositorio.existsByUsername(usuario.getUsername())) {
            return ResponseEntity.badRequest().body("El username ya está registrado.");
        }

     
        
        
        usuario.setFechaRegistro(LocalDateTime.now());
        usuario.setActivo(true);
        usuario.setIntentosFallidos(0);
        usuario.setVerificacion2fa(false);

        
        usuario.setPersona(personaOpt.get());

       
        Usuario usuarioGuardado = usuarioRepositorio.save(usuario);

        return ResponseEntity.ok(usuarioGuardado);
    }
    
}
