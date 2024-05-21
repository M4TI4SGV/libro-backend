package com.example.librobackend.service;

import com.example.librobackend.dto.LibroDTO;
import com.example.librobackend.model.Libro;
import com.example.librobackend.repository.LibroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class LibroService implements ILibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public LibroDTO crearLibro(LibroDTO libroDTO) {
        Libro libro = modelMapper.map(libroDTO, Libro.class);
        libro = libroRepository.save(libro);
        return modelMapper.map(libro, LibroDTO.class);
    }

    @Override
    public LibroDTO editarLibro(Long id, LibroDTO libroDTO) {
        Libro existingLibro = libroRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Libro no encontrado con id: " + id));
        modelMapper.map(libroDTO, existingLibro);
        existingLibro = libroRepository.save(existingLibro);
        return modelMapper.map(existingLibro, LibroDTO.class);
    }

    @Override
    public List<LibroDTO> traerTodos() {
        List<Libro> libros = libroRepository.findByDeletedFalse();
        return libros.stream()
                .map(libro -> modelMapper.map(libro, LibroDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public LibroDTO traerPorId(Long id) {
        Libro libro = libroRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Libro no encontrado con id: " + id));
        return modelMapper.map(libro, LibroDTO.class);
    }

    @Override
    public void eliminarLibro(Long id) {
        Libro libro = libroRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Libro no encontrado con id: " + id));
        libro.setDeleted(true);
        libroRepository.save(libro);
    }
}
