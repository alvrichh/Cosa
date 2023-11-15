package com.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.model.Libro;

@Repository
public interface LibroRepositorio extends CrudRepository<Libro, Integer> {

}
