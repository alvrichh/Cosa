package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.model.CuentaUsuario;
import com.model.Empleado;
import com.service.EmpleadoService;
import com.github.javafaker.Faker;

@Component
public class InicializarDatos implements CommandLineRunner {

    @Autowired
    private EmpleadoService servicio;

    @Override
    public void run(String... args) {
        Faker faker = new Faker();

        for (int i = 0; i < 10; i++) {
            Empleado empleado = new Empleado();
            empleado.setNombre(faker.name().fullName());

            CuentaUsuario cuenta = new CuentaUsuario();
            cuenta.setPassword(faker.internet().password());
            cuenta.setUsername(faker.name().username());

            servicio.crearEmpleadoConCuentaUsuario(empleado, cuenta);
        }
    }
}
