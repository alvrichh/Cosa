package com.model;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.service.ServicioLibro;

import jakarta.annotation.PostConstruct;

@Component
public class InicializarDatos {

	@Autowired
	private ServicioLibro servicioLibro;


	@PostConstruct
	public void init() {
		// crear y guardar algunos coches de ejemplo
		if(servicioLibro.listarTodosLosLibros().iterator().hasNext()) {
			return;
		}
        Libro l1 = new Libro(1, "Las rosas de Guadalupe", "Marco Antonio Zarza", "ISBN123", 2000);
        Libro l2 = new Libro(2, "Cien años de soledad", "Gabriel García Márquez", "ISBN456", 1967);
        Libro l3 = new Libro(3, "El Aleph", "Jorge Luis Borges", "ISBN789", 1949);
		servicioLibro.guardarLibro(l1);
		servicioLibro.guardarLibro(l2);
		servicioLibro.guardarLibro(l3);


	}
}
