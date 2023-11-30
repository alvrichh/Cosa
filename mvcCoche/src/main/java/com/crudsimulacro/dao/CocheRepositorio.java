package com.crudsimulacro.dao;

import com.crudsimulacro.model.Coche;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  CocheRepositorio extends CrudRepository<Coche, Integer> {

}
