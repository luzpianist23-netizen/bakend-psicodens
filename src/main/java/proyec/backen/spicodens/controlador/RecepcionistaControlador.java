package proyec.backen.spicodens.controlador;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import org.springframework.web.bind.annotation.*;

import proyec.backen.spicodens.modelo.Recepcionista;
import proyec.backen.spicodens.repositorio.RecepcionistaRepositorio;

import java.util.List;

@RestController
@RequestMapping("/api/recepcionistas")
public class RecepcionistaControlador {

    @Autowired
    private RecepcionistaRepositorio recepcionistaRepositorio;

    // Listar todos los recepcionistas ordenados por ID
    @GetMapping
    public List<Recepcionista> listarRecepcionistas() {
        return recepcionistaRepositorio.findAll(Sort.by("id"));
    }

       // Registrar recepcionista
    @PostMapping
    public Recepcionista registrarRecepcionista(@RequestBody Recepcionista recepcionista) {
        return recepcionistaRepositorio.save(recepcionista);
    }
}
