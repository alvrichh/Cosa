package com.crudsimulacro.service;

import com.crudsimulacro.dao.CocheRepositorio;
import com.crudsimulacro.model.Coche;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CocheServicio {

    @Autowired
    private CocheRepositorio repositorio;
    public List<Coche> obtenerTodos() {

        List<Coche> coches = new ArrayList<Coche>();
        Iterable<Coche> coches_iterator = repositorio.findAll();

        for (Coche coche : coches_iterator) {
            coches.add(coche);
        }

        return coches;
    }

    public void guardar(Coche coche) {
        repositorio.save(coche);
    }

    public Coche obtenerCochePorId(Integer id) {
        Optional<Coche> coche = repositorio.findById(id);
        return coche.get();
    }

    public void eliminar(Integer id) {
        repositorio.deleteById(id);
    }
}
