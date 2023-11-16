package com.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.repositorio.LibroRepositorio;

/**
 * Clase de prueba para {@link LibroRepositorio} usando {@code @DataJpaTest} para configurar un contexto de prueba de JPA.
 */
@DataJpaTest
public class LibroRepositorioTest {

    @Autowired
    private TestEntityManager entityManager;

    // El repositorio que se está probando
    @Autowired
    private LibroRepositorio libroRepositorio;

    // Un libro de prueba que se usará en los métodos de prueba
    private Libro libro;

    /**
     * Método que se ejecuta antes de cada prueba para configurar los objetos necesarios.
     */
    @BeforeEach
    void setUp() {
        libro = new Libro();
        libro.setTitulo("Test");
        libro.setTitulo("Test");
        libro.setAutor("Test");
        libro.setIsbn("Test");
        libro.setPublicadoEn(2000);

        entityManager.persist(libro);
        entityManager.flush();
    }

    /**
     * Prueba que verifica el correcto funcionamiento de {@link LibroRepositorio#save(Object)}.
     */
    @Test
    public void testGuardarLibro() {
        Libro nuevoLibro = new Libro();

        nuevoLibro.setTitulo("Test");

        Libro guardado = libroRepositorio.save(nuevoLibro);
        assertThat(guardado).hasFieldOrPropertyWithValue("titulo", "Test");
    }

    /**
     * Prueba que verifica el correcto funcionamiento de {@link LibroRepositorio#findById(Object)}.
     */
    @Test
    public void testBuscarLibroPorId() {
        Libro encontrado = libroRepositorio.findById(libro.getId()).orElse(null);
        assertThat(encontrado).isEqualTo(libro);
    }

    /**
     * Prueba que verifica el correcto funcionamiento de {@link LibroRepositorio#findAll()}.
     */
    @Test
    public void testListarLibros() {
        List<Libro> libros = (List<Libro>) libroRepositorio.findAll();
        assertThat(libros).isNotEmpty();
        assertThat(libros).contains(libro);
    }

    /**
     * Prueba que verifica el correcto funcionamiento de {@link LibroRepositorio#delete(Object)} y {@link LibroRepositorio#findById(Object)}.
     */
    @Test
    public void testEliminarLibro() {
        libroRepositorio.delete(libro);
        Libro eliminado = libroRepositorio.findById(libro.getId()).orElse(null);
        assertThat(eliminado).isNull();
    }
}
