package com.example.demo.servicio;

import java.util.List;
import java.util.Set;

import com.example.demo.entidad.Autor;
import com.example.demo.entidad.Libro;


public interface ServicioLibreria {
	Libro guardarLibro(Libro libro);
	void eliminarLibro(Long id);
	Libro obtenerLibroPorId(Long id);
	List<Libro> listarTodosLosLibros();
	Libro actualizarLibro(Long id, Libro libro);
	Set<Autor> obtenerAutores();
	Autor obternerAutor(Long id);
	Autor guardarAutor(Autor autor);
}
