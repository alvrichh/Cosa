package com.example.coches.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.coches.entidad.Coche;
import com.example.coches.entidad.FuenteEnergia;
import com.example.coches.entidad.Idioma;
import com.example.coches.servicios.ServicioCoches;

import jakarta.validation.Valid;
/**
 * @author Álvaro Rodríguez Molina
 */
@Controller
public class ControladorCoche {

	@Autowired
	private ServicioCoches servicioCoches;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("mensaje", " DWES :: RA3");
		model.addAttribute("idiomas", Idioma.values());
		return "index";
	}

	@GetMapping("/idioma")
	public String idioma(@RequestParam String idioma) {
		if (idioma.equals(Idioma.EN.toString())) {
			return "redirect:/?lang=en";
		} else {
			return "redirect:/?lang=es";
		}
	}

	@GetMapping("/coches")
	public String listarCoches(Model model) {
		List<Coche> coches = servicioCoches.obtenerTodos();
		model.addAttribute("coches", coches);
		model.addAttribute("fuenteEnergia", servicioCoches.obtenerFuentesEnergia());
		model.addAttribute("idiomas", Idioma.values()); //con esto puedo cambiar el idioma desde la lista

		return "coches";
	}

	@GetMapping("/filtrar")
	public String filtrarCoches(@RequestParam FuenteEnergia fuenteEnergia, Model model) {
		List<Coche> coches = servicioCoches.filtrarFuenteEnergia(fuenteEnergia);
		model.addAttribute("coches", coches);
		model.addAttribute("fuenteEnergia", servicioCoches.obtenerFuentesEnergia()); // aquí recoge los valores del enum
		return "coches";
	}

	@GetMapping("/formulario")
	public String formularioCoches(Model model) {
		model.addAttribute("coche", new Coche()); // Crea un nuevo coche
		model.addAttribute("fuenteEnergia", servicioCoches.obtenerFuentesEnergia());
		return "formulario";

	}

	@PostMapping("/nuevo_coche")
	public String agregarCoche(@Valid @ModelAttribute("coche") Coche coche, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("fuenteEnergia", servicioCoches.obtenerFuentesEnergia());
			return "formulario";
		}
		servicioCoches.guardar(coche);
		return "redirect:/coches"; // redirije a la lista de los coches
	}

}
