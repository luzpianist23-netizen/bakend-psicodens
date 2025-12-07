package proyec.backen.spicodens.controlador;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import proyec.backen.spicodens.modelo.ServicioOdontologico;
import proyec.backen.spicodens.repositorio.ServicioOdontologicoRepositorio;

import java.util.List;


@RestController
@RequestMapping("/api/serviciosOdontologicos")
public class ServicioOdontologicoControlador {

    @Autowired
    private ServicioOdontologicoRepositorio servicioOdontologicoRepositorio;

    // ====== Listar todos los servicios ======
    @GetMapping
    public List<ServicioOdontologico> listarServicios() {
        return servicioOdontologicoRepositorio.findAll(Sort.by("id"));
    }
    
    // ====== Registrar un nuevo servicio ======
    @PostMapping
    public ServicioOdontologico registrarServicio(@RequestBody ServicioOdontologico servicio) {
        return servicioOdontologicoRepositorio.save(servicio);
    }
}
