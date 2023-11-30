package com.crudsimulacro.controller;

import com.crudsimulacro.service.CocheServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.crudsimulacro.model.Coche;



import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CocheControladorTest {
    @Autowired
    private MockMvc mockmvc;

    @MockBean
    private CocheServicio servicio;
    Coche coche = new Coche();

    @BeforeEach
    public void setUp() {
        coche.setId(1);
        coche.setMarca("Toyota");
        coche.setMatricula("123456A");
        coche.setColor("Rojo");
    }
    @Test
    public void verificar_listado() throws Exception {
        List<Coche> coches = Arrays.asList(coche);

        given(servicio.obtenerTodos()).willReturn(coches);

        mockmvc.perform(get("/coches"))
                .andExpect(view().name("coches"))
                .andExpect(model().attributeExists("coches"))
                .andExpect(model().attribute("coches", coches));
    }

    @Test
    public void agregar_CuandoCocheNoEsValido_VolverAlFormulario() throws Exception {

        mockmvc.perform(post("/coches/agregar")
                .flashAttr("coche", coche))
                //No hace falta, va implicito en la linea de abajo "redirectedUrl" .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/coches"));
    }

}