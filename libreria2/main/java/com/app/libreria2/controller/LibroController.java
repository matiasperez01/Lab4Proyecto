package com.app.libreria2.controller;

import com.app.libreria2.model.Libro;
import com.app.libreria2.model.User;
import com.app.libreria2.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class LibroController {

    @Autowired
    private LibroRepository repository;

    @GetMapping("/libros")
    public Iterable<Libro> findAllLibros() {
        return repository.findAll();
    }

    @GetMapping("/libros/{id}")
    public Optional<Libro> findLibroById(@PathVariable("id")int id) {
        return repository.findById(id);
    }

    @PostMapping("/libros")
    public Libro createLibro(@RequestBody Libro libro) {
        return repository.save(libro);
    }

    @PutMapping("/libros/{id}")
    public Libro updateLibro(@PathVariable("id") @RequestBody Libro libro){
        return repository.save(libro);
    }

    @DeleteMapping("/libros/{id}")
    public void deleteLibro(@PathVariable("id") int id){
        repository.deleteById(id);
    }
}
