package com.crudsimulacro.controller;

import com.crudsimulacro.model.Coche;
import com.crudsimulacro.service.CocheServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CocheControlador {
    @Autowired
    private CocheServicio servicio;
    @GetMapping("/coches")
    public String mostrarListado(Model model) {
        model.addAttribute("coches", servicio.obtenerTodos());
        return "coches";
    }

    @GetMapping("/coches/nuevo")
    public String formCoche(Model model) {
        model.addAttribute("coche", new Coche());
        return "form_coche";
    }

    @PostMapping("/coches/agregar")
    public String crearCoche(Model model, @ModelAttribute("coche") @Valid Coche coche, BindingResult result) {
        if (result.hasErrors()) {
            return "form_coche";
        }
        servicio.guardar(coche);

        return "redirect:/coches";
    }

    @GetMapping("/coches/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        Coche coche = servicio.obtenerCochePorId(id);
        model.addAttribute("coche", coche);
        return "form_coche";
    }

    @GetMapping("/coches/eliminar/{id}")
    public String eliminarCoche(@PathVariable Integer id, Model model) {
        servicio.eliminar(id);

        return "redirect:/coches";
    }


}
