package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Libro;
import com.repositorio.LibroRepositorio;

@Service
public class ServicioLibro {

	@Autowired
	private LibroRepositorio libroRepositorio;

	public ServicioLibro(LibroRepositorio libroRepositorio) {
		this.libroRepositorio = libroRepositorio;
	}
	public Iterable<Libro> listarTodosLosLibros(){
		return libroRepositorio.findAll();
	}
	public Libro guardarLibro(Libro libro) {
		return libroRepositorio.save(libro);
	}
	public Libro obtenerLibroPorId(Integer id) {
		return libroRepositorio.findById(id).orElseThrow(() ->
		new IllegalArgumentException("Libro no encontrado con id: "+ id));
	}
	public void eliminarLibro(Integer id) {
		libroRepositorio.deleteById(id);
	}

}
