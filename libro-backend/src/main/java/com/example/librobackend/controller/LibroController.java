package com.example.librobackend.controller;

import com.example.librobackend.dto.LibroDTO;
import com.example.librobackend.service.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private ILibroService libroService;

    @PostMapping
    public LibroDTO crearLibro(@RequestBody LibroDTO libroDTO) {
        return libroService.crearLibro(libroDTO);
    }

    @PutMapping("/{id}")
    public LibroDTO editarLibro(@PathVariable Long id, @RequestBody LibroDTO libroDTO) {
        return libroService.editarLibro(id, libroDTO);
    }

    @GetMapping
    public List<LibroDTO> traerTodos() {
        return libroService.traerTodos();
    }

    @GetMapping("/{id}")
    public LibroDTO traerPorId(@PathVariable Long id) {
        return libroService.traerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarLibro(@PathVariable Long id) {
        libroService.eliminarLibro(id);
    }
}
