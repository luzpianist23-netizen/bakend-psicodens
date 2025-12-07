package proyec.backen.spicodens.controlador;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import org.springframework.web.bind.annotation.*;

import proyec.backen.spicodens.modelo.Profesional;
import proyec.backen.spicodens.repositorio.ProfesionalRepositorio;

import java.util.List;


@RestController
@RequestMapping("/api/profesionales")
public class ProfesionalControlador {

    @Autowired
    private ProfesionalRepositorio profesionalRepositorio;

    @GetMapping
    public List<Profesional> listarProfesionales() {
        return profesionalRepositorio.findAll(Sort.by("id"));
    }
}