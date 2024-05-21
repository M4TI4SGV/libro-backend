package com.example.librobackend.service;

import com.example.librobackend.dto.LibroDTO;

import java.util.List;

public interface ILibroService {
    LibroDTO crearLibro(LibroDTO libroDTO);
    LibroDTO editarLibro(Long id, LibroDTO libroDTO);
    List<LibroDTO> traerTodos();
    LibroDTO traerPorId(Long id);
    void eliminarLibro(Long id);
}
