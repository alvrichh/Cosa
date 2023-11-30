package com.crudsimulacro.data;

// @TODO me falta importar la config

import com.crudsimulacro.dao.CocheRepositorio;
import com.crudsimulacro.model.Coche;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InicializarDatos implements CommandLineRunner {

    @Autowired
    CocheRepositorio repositorio;

    // El siguiente comando se ejecuta antes que nada
    @Override
    public void run(String... args) throws Exception {
        Coche coche1 = new Coche();
        coche1.setColor("Azul");
        coche1.setMatricula("789012B");
        coche1.setMarca("Toyota");
        repositorio.save(coche1);

        Coche coche2 = new Coche();
        coche2.setColor("Verde");
        coche2.setMatricula("345678C");
        coche2.setMarca("Ford");
        repositorio.save(coche2);

        Coche coche3 = new Coche();
        coche3.setColor("Amarillo");
        coche3.setMatricula("901234D");
        coche3.setMarca("Chevrolet");
        repositorio.save(coche3);

        Coche coche4 = new Coche();
        coche4.setColor("Gris");
        coche4.setMatricula("567890E");
        coche4.setMarca("Honda");
        repositorio.save(coche4);

        Coche coche5 = new Coche();
        coche5.setColor("Negro");
        coche5.setMatricula("123456F");
        coche5.setMarca("Volkswagen");
        repositorio.save(coche5);
    }
}
