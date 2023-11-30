package com.example.demo.entidad;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.enumerado.Categoria;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@SpringBootTest
public class LibroTests {

    @Test
    public void testLibroValido() {
        Libro libro = new Libro();
        libro.setTitulo("El título del libro");
        libro.setCategoria(Categoria.TEST);

        // No debería lanzar ninguna excepción de validación
        assertDoesNotThrow(() -> validarLibro(libro));
    }

    @Test
    public void testTituloVacio() {
        Libro libro = new Libro();
        libro.setTitulo(""); // Título vacío

        // Debería lanzar una excepción de validación
        assertThrows(ConstraintViolationException.class, () -> validarLibro(libro));
    }

    @Test
    public void testTituloLargo() {
        Libro libro = new Libro();
        libro.setTitulo("Este título es demasiado largo y excede los 100 caracteres permitidos.");

        // Debería lanzar una excepción de validación
        assertThrows(ConstraintViolationException.class, () -> validarLibro(libro));
    }

    @Test
    public void testRelacionConAutor() {
        Libro libro = new Libro();
        Autor autor = new Autor();
        libro.setAutor(autor);

        // Verificar que la relación con Autor está correctamente configurada
        assertNotNull(libro.getAutor());
        assertEquals(autor, libro.getAutor());
    }

    private void validarLibro(Libro libro) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Libro>> violations = validator.validate(libro);

        // Verificar si hay violaciones de restricciones
        if (!violations.isEmpty()) {
            // Aquí puedes manejar las violaciones según tus necesidades (lanzar una excepción, loggear, etc.)
            throw new ConstraintViolationException(violations);
        }
    }
}
