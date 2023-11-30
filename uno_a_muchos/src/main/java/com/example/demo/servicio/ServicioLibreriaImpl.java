package com.example.demo.servicio;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidad.Autor;
import com.example.demo.entidad.Libro;
import com.example.demo.repositorio.AutorRepositorio;
import com.example.demo.repositorio.LibroRepositorio;

@Service
public class ServicioLibreriaImpl implements ServicioLibreria {

	@Autowired
	private AutorRepositorio autorRepositorio;

	@Autowired
	LibroRepositorio libroRepositorio;

	@Override
	public Libro guardarLibro(Libro libro) {
		return libroRepositorio.save(libro);
	}

	@Override
	public void eliminarLibro(Long id) {
		libroRepositorio.deleteById(id);

	}

	@Override
	public Libro obtenerLibroPorId(Long id) {
		return libroRepositorio.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Libro no encontrado por Id" + id));
	}

	@Override
	public List<Libro> listarTodosLosLibros() {
		return libroRepositorio.findAll();
	}

	@Override
	public Libro actualizarLibro(Long id, Libro libro) {
		Libro libroExistente = libroRepositorio.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Libro no encontrado por Id" + id));
	    libroExistente.setTitulo(libro.getTitulo());
	    libroExistente.setAutor(libro.getAutor());
	    libroExistente.setCategoria(libro.getCategoria());
		return libroRepositorio.save(libroExistente);
	}

	@Override
	public Set<Autor> obtenerAutores() {
		return new HashSet<>(autorRepositorio.findAll());
	}

	@Override
	public Autor obternerAutor(Long id) {
		return autorRepositorio.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Libro no encontrado por Id" + id));
	}
    @Override
    public Autor guardarAutor(Autor autor) {
    	return autorRepositorio.save(autor);

    }
}

	 

