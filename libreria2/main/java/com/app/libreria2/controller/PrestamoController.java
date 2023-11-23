package com.app.libreria2.controller;

import com.app.libreria2.model.Libro;
import com.app.libreria2.model.Prestamo;
import com.app.libreria2.repository.LibroRepository;
import com.app.libreria2.repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class PrestamoController {

    @Autowired
    private PrestamoRepository repository;

    @GetMapping("/prestamos")
    public Iterable<Prestamo> findAllPrestamos() {
        return repository.findAll();
    }

    @GetMapping("/prestamos/{id}")
    public Optional<Prestamo> findPrestamoById(@PathVariable("id")int id) {
        return repository.findById(id);
    }

    @PostMapping("/prestamos")
    public Prestamo createPrestamo(@RequestBody Prestamo prestamo) {
        return repository.save(prestamo);
    }

    @PutMapping("/prestamos/{id}")
    public Prestamo updatePrestamo(@PathVariable("id") @RequestBody Prestamo prestamo){
        return repository.save(prestamo);
    }

    @DeleteMapping("/prestamos/{id}")
    public void deletePrestamo(@PathVariable("id") int id){
        repository.deleteById(id);
    }


}
