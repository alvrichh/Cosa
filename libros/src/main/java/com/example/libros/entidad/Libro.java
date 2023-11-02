package com.example.libros.entidad;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Libro {

	private Long id;

	@NotBlank(message = "{titulo.notblank}")
	@Size(min = 1, max = 100, message = "{autor.size}")
	private String autor;
	////////////////////////////////////
	@NotBlank(message = "El nombre no puede estar vacío.")
	@Size(min = 1, max = 100, message = "{titulo.size}")
	private String nombre;
	///////////////////////////////////

	private Double precio;

	@NotNull(message = "EL año no puede estar vacío.")
	private int anyoPublicacion;

	@NotNull(message = "La categoria es requerida.")
	private Categoria categoria;
	//////////////////////////////////
	@NotBlank(message="{isbn.notblank}")
	@Pattern(regexp="ISBN(?:-1[03])?:?\\ (?=[-0-9xX ]{13}$|[-0-9X]{17}$|979[-0-9 ]{10}$|978[-0-9 ]{10}$)(?:97[89][- ]?)?[0-9]+[- ]?[0-9]+[-]?[0-9]+[-]?[0-9 X]", message = "{isbn.pattern}")
	private String isbn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public int getAnyoPublicacion() {
		return anyoPublicacion;
	}

	public void setAnyoPublicacion(int anyoPublicacion) {
		this.anyoPublicacion = anyoPublicacion;
	}

}
