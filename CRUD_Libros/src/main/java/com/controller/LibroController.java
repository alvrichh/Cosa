package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model.Idioma;
import com.model.Libro;
import com.service.ServicioLibro;

import jakarta.validation.Valid;

/**
 * @author Álvaro Rodríguez Molina
 */
@Controller
public class LibroController {

	@Autowired
	ServicioLibro servicioLibro;
	/**
	 * Nos muestra la lista de los libros
	 * 
	 * @param model
	 * @return se dirije a la página index.html
	 */
	@GetMapping("/")
	public String listarLibros(Model model) {
		model.addAttribute("libros", servicioLibro.listarTodosLosLibros());
		model.addAttribute("idiomas", Idioma.values());
		return "index";
	}

	/*
 	@GetMapping("/idioma")
	public String idioma(@RequestParam String idioma) {
		if (idioma.equals(Idioma.EN.toString())) {
			return "redirect:/?lang=en";
		} else {
			return "redirect:/?lang=es";
		}
	}
	*/
	
	/**
	 * Nos muestra el formulario de creación de libros
	 * 
	 * @param model
	 * @return vuelve al formulario de creación de libros
	 */
	@GetMapping("/libro")
	public String mostrarFormulario(Model model) {
		Libro libro = new Libro();
		model.addAttribute("libro", libro);
		
		return "libro-form";
	}

	/**
	 * Editar libro que ya existe
	 * 
	 * @param id
	 * @param model
	 * @return vuelve al formulario de los libros para editar la información
	 */

	@GetMapping("/libro/editar/{id}")
	public String mostrarFormularioEdit(@PathVariable Integer id, Model model) {
		Libro libro = servicioLibro.obtenerLibroPorId(id);
		model.addAttribute("libro", libro);
		return "libro-form";
	}

	/**
	 * Si se ha podido guardar el libro nos dirigimos a la página anterior que en
	 * este caso es index, donde se muestran los libros.
	 * 
	 * @param libro
	 * @param result
	 * @param model
	 * @return si tiene errores nos vuelve a dirigir al formulario de nuevo libro
	 *         con los mensajes de validación de la entidad Libro declaradas en la
	 *         calse; @Noblank
	 */
	@PostMapping("/libro")
	public String guardarLibro(@Valid @ModelAttribute("libro") Libro libro, BindingResult result, Model model) {
		if (result.hasFieldErrors()) {
			return "libro-form";
		}
		servicioLibro.guardarLibro(libro);
		return "redirect:/";
	}

	/**
	 * ELiminar libros seleccionando su ID, por eso se utiliza PathVariable, y se
	 * elimina el libro seleccionado
	 * @param id
	 * @return nos redirije hacia la página anterior, que sería la de mostrar libros
	 */
	@GetMapping("/libro/eliminar/{id}")
	public String eliminarLibro(@PathVariable Integer id) {
		servicioLibro.eliminarLibro(id);
		return "redirect:/";

	}
}
