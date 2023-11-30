package com.example.demo.integracion;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entidad.Autor;
import com.example.demo.entidad.Libro;
import com.example.demo.enumerado.Categoria;
import com.example.demo.servicio.ServicioLibreria;

import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ServicioLibreriaIntegrationTest {

    @Autowired
    private ServicioLibreria servicioLibreria;

    @Test
    public void testListarLibros() {
        // Asumiendo que la base de datos ya tiene libros
        List<Libro> libros = servicioLibreria.listarTodosLosLibros();
        assertFalse(libros.isEmpty());
    }

    @Test
    public void testActualizarLibro() {
        // Asumiendo que hay un libro en la base de datos con id conocido
        Long libroId = 1L; // Cambia esto por un ID real de tu base de datos
        Libro libro = servicioLibreria.obtenerLibroPorId(libroId);
        libro.setTitulo("Nuevo Título");
        servicioLibreria.actualizarLibro(libroId, libro);

        Libro libroActualizado = servicioLibreria.obtenerLibroPorId(libroId);
        assertEquals("Nuevo Título", libroActualizado.getTitulo());
    }


 
}
