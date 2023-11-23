package com.app.libreria2.repository;

import com.app.libreria2.model.Libro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends CrudRepository<Libro, Integer> {

}
