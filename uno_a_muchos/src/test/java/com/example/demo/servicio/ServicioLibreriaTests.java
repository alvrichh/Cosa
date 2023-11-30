package com.example.demo.servicio;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.entidad.Libro;
import com.example.demo.repositorio.AutorRepositorio;
import com.example.demo.repositorio.LibroRepositorio;

@SpringBootTest
public class ServicioLibreriaTests {

    @Autowired
    private ServicioLibreriaImpl servicioLibreria;

    @MockBean
    private LibroRepositorio libroRepositorio;

    @MockBean
    private AutorRepositorio autorRepositorio;

    @Test
    public void testGuardarLibro() {
        // Configurar el comportamiento del repositorio mock
        when(libroRepositorio.save(any(Libro.class))).thenReturn(new Libro());

        // Ejecutar el método del servicio
        Libro libroGuardado = servicioLibreria.guardarLibro(new Libro());

        // Verificar que el libro fue guardado correctamente
        assertNotNull(libroGuardado);
        verify(libroRepositorio, times(1)).save(any(Libro.class));
    }

    @Test
    public void testObtenerLibroPorIdExistente() {
        // Configurar el comportamiento del repositorio mock
        Long idLibroExistente = 1L;
        Libro libroMock = new Libro();
        when(libroRepositorio.findById(idLibroExistente)).thenReturn(Optional.of(libroMock));

        // Ejecutar el método del servicio
        Libro libroObtenido = servicioLibreria.obtenerLibroPorId(idLibroExistente);

        // Verificar que el libro fue obtenido correctamente
        assertNotNull(libroObtenido);
        verify(libroRepositorio, times(1)).findById(idLibroExistente);
    }

    @Test
    public void testObtenerLibroPorIdNoExistente() {
        // Configurar el comportamiento del repositorio mock
        Long idLibroNoExistente = 99L;
        when(libroRepositorio.findById(idLibroNoExistente)).thenReturn(Optional.empty());

        // Ejecutar el método del servicio y esperar una excepción
        assertThrows(IllegalArgumentException.class,
                () -> servicioLibreria.obtenerLibroPorId(idLibroNoExistente));

        // Verificar que se llamó al método findById del repositorio
        verify(libroRepositorio, times(1)).findById(idLibroNoExistente);
    }
}
