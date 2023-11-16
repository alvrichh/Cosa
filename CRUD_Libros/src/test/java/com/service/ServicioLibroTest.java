package com.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.model.Libro;
import com.repositorio.LibroRepositorio;

/**
 * Clase de prueba para {@link ServicioLibro} utilizando {@code @SpringBootTest}
 * para configurar un contexto de prueba de Spring Boot.
 */
@SpringBootTest
public class ServicioLibroTest {

	private ServicioLibro servicioLibro;

	@Mock
	private LibroRepositorio libroRepositorio;

	/**
	 * Método que se ejecuta antes de cada prueba para configurar los objetos
	 * necesarios.
	 */
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		servicioLibro = new ServicioLibro(libroRepositorio);
	}

	/**
     * Prueba que verifica el correcto funcionamiento de {@link ServicioLibro#listarTodosLosLibros()}.
     */
    @Test
    void testListarLibros() {
        when(libroRepositorio.findAll()).thenReturn(Arrays.asList(new Libro()));

        Iterable<Libro> libros_it = servicioLibro.listarTodosLosLibros();

        List<Libro> libros = new ArrayList<>();
        libros_it.forEach(libros::add);

        assertEquals(1, libros.size(), "Debe haber un libro en la lista");
        verify(libroRepositorio).findAll(); // Verifica que se llame al método findAll del repositorio
    }

	/**
	 * Prueba que verifica el correcto funcionamiento de
	 * {@link ServicioLibro#obtenerLibroPorId(Integer)}.
	 */
	@Test
	void testObtenerProductoPorId() {
		// Dado
		Libro libro = new Libro();
		libro.setId(1);
		when(libroRepositorio.findById(1)).thenReturn(Optional.of(libro));

		// Cuando
		Libro encontrado = servicioLibro.obtenerLibroPorId(1);

		// Entonces
		assertEquals(libro, encontrado, "El libro encontrado debe coincidir con el mock");
		verify(libroRepositorio).findById(1); // Verifica que se llame al método findById del repositorio
	}

	/**
	 * Prueba que verifica el correcto funcionamiento de
	 * {@link ServicioLibro#guardarLibro(Libro)}.
	 */
	@Test
	void testGuardarLibro() {
		// Dado
		Libro libro = new Libro();
		when(libroRepositorio.save(any(Libro.class))).thenReturn(libro);

		// Cuando
		Libro guardado = servicioLibro.guardarLibro(libro);

		// Entonces
		assertEquals(libro, guardado, "El libro guardado debe coincidir con el mock");
		verify(libroRepositorio).save(any(Libro.class)); // Verifica que se llame al método save del repositorio
	}

	/**
	 * Prueba que verifica el correcto funcionamiento de
	 * {@link ServicioLibro#eliminarLibro(Integer)}.
	 */
	@Test
	void testEliminarProducto() {
		// Dado
		Integer id = 1;

		// Cuando
		servicioLibro.eliminarLibro(id);

		// Entonces
		verify(libroRepositorio, times(1)).deleteById(id); // Verifica que se llame al método deleteById del repositorio
	}
}
