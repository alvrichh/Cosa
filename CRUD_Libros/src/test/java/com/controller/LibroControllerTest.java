package com.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.model.Libro;
import com.service.ServicioLibro;
/**
 * Pruebas unitarias para {@link LibroController}.
 */
@WebMvcTest(LibroController.class)
public class LibroControllerTest {

    // Inyección de dependencias para MockMvc
    @Autowired
    private MockMvc mockMvc;

    // MockBean para el servicio de libros
    @MockBean
    private ServicioLibro servicioLibro;

    // Objeto de prueba para libros
    private Libro libro;

    /**
     * Configuración inicial antes de cada prueba.
     */
    @BeforeEach
    void setUp() {
        libro = new Libro();
        libro.setId(1);
        libro.setTitulo("Test");
        libro.setAutor("Test");
        libro.setIsbn("Test");
        libro.setPublicadoEn(0000);
    }

    /**
     * Prueba para verificar el listado de libros.
     *
     * @throws Exception si hay un error durante la ejecución de la prueba.
     */
    @Test
    void testListarLibros() throws Exception {
        List<Libro> libros = Arrays.asList(libro);
        given(servicioLibro.listarTodosLosLibros()).willReturn(libros);

        mockMvc.perform(get("/"))
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("libros"))
                .andExpect(model().attribute("libros", libros));
    }

    /**
     * Prueba para mostrar el formulario de libro.
     *
     * @throws Exception si hay un error durante la ejecución de la prueba.
     */
    @Test
    void testMostrarFormulario() throws Exception {
        mockMvc.perform(get("/libro"))
                .andExpect(view().name("libro-form"))
                .andExpect(model().attributeExists("libro"));
    }

    /**
     * Prueba para mostrar el formulario de edición de libro.
     *
     * @throws Exception si hay un error durante la ejecución de la prueba.
     */
    @Test
    void testMostrarFormularioEdit() throws Exception {
        given(servicioLibro.obtenerLibroPorId(any())).willReturn(libro);

        mockMvc.perform(get("/libro/editar/{id}", libro.getId()))
                .andExpect(view().name("libro-form"))
                .andExpect(model().attributeExists("libro"))
                .andExpect(model().attribute("libro", libro));
    }

    /**
     * Prueba para guardar un libro.
     *
     * @throws Exception si hay un error durante la ejecución de la prueba.
     */
    @Test
    void testGuardarLibro() throws Exception {
        mockMvc.perform(post("/libro").flashAttr("libro", libro))
                .andExpect(redirectedUrl("/"));
    }

    /**
     * Prueba para eliminar un libro.
     *
     * @throws Exception si hay un error durante la ejecución de la prueba.
     */
    @Test
    void testEliminarLibro() throws Exception {
        mockMvc.perform(get("/libro/eliminar/{id}", libro.getId()))
                .andExpect(redirectedUrl("/"));
    }
}
