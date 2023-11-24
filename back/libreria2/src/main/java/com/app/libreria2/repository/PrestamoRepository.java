package com.app.libreria2.repository;

import com.app.libreria2.model.Libro;
import com.app.libreria2.model.Prestamo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepository extends CrudRepository<Prestamo, Integer> {

}
