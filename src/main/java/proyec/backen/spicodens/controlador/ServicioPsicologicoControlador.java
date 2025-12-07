package proyec.backen.spicodens.controlador;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import proyec.backen.spicodens.modelo.ServicioPsicologico;
import proyec.backen.spicodens.repositorio.ServicioPsicologicoRepositorio;

import java.util.List;


@RestController
@RequestMapping("/api/serviciosPsicologicos")
public class ServicioPsicologicoControlador {

    @Autowired
    private ServicioPsicologicoRepositorio servicioPsicologicoRepositorio;

    // ====== Listar todos los servicios ======
    @GetMapping
    public List<ServicioPsicologico> listarServicios() {
        return servicioPsicologicoRepositorio.findAll(Sort.by("id"));
    }
    
    // ====== Registrar un nuevo servicio ======
    @PostMapping
    public ServicioPsicologico registrarServicio(@RequestBody ServicioPsicologico servicio) {
        return servicioPsicologicoRepositorio.save(servicio);
    }
    }