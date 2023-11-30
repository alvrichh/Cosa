package com.crudsimulacro;

import com.crudsimulacro.model.Coche;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CocheUnitTest {

    private Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void whenMarcaIsBlank_thenConstraintViolation() {
        Coche coche = new Coche();
        coche.setMarca("");
        coche.setMatricula("123456A");
        coche.setColor("Rojo");

        Set<ConstraintViolation<Coche>> violations = validator.validate(coche);
        System.out.println("################");

        assertFalse(violations.isEmpty());
    }

    @Test
    void setgetter_Coche() {
        Coche coche = new Coche();
        coche.setMarca("Renault");
        coche.setMatricula("123456A");
        coche.setColor("Rojo");

        assertEquals("Renault", coche.getMarca(), "La marca no corresponde");
        assertEquals("123456A", coche.getMatricula(), "La matricula no corresponde");
        assertEquals("Rojo", coche.getColor(), "El color no corresponde");
    }
}
