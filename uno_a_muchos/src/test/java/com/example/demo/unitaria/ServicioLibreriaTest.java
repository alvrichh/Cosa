package com.example.demo.unitaria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.entidad.Autor;
import com.example.demo.entidad.Libro;
import com.example.demo.repositorio.AutorRepositorio;
import com.example.demo.repositorio.LibroRepositorio;
import com.example.demo.servicio.ServicioLibreriaImpl;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ServicioLibreriaTest {

    @Mock
    private LibroRepositorio libroRepositorio;
    @Mock
    private AutorRepositorio autorRepositorio;

    @InjectMocks
    private ServicioLibreriaImpl servicioLibreria;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGuardarLibro() {
        Libro libro = new Libro();
        when(libroRepositorio.save(any(Libro.class))).thenReturn(libro);

        Libro resultado = servicioLibreria.guardarLibro(libro);

        assertNotNull(resultado);
        verify(libroRepositorio).save(libro);
    }

    @Test
    void testObtenerLibroPorId() {
        Long id = 1L;
        Libro libro = new Libro(); // asumir constructor adecuado y setters
        libro.setId(id);
        when(libroRepositorio.findById(id)).thenReturn(Optional.of(libro));

        Libro resultado = servicioLibreria.obtenerLibroPorId(id);

        assertEquals(id, resultado.getId());
    }
}
