package com.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
public class Libro {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	// Validar que sea correcto
	
	@NotBlank(message = "{error.titulo}") // validamos que el campo no esté vacío
	private String titulo;

	@NotBlank(message = "{error.autor}") // validamos que el campo no esté vacío
	private String autor;

	@NotBlank(message = "{error.isbn}") // validamos que el campo no esté vacío
	private String isbn;

	@NotNull(message = "{error.publicadoEn}")
	private Integer publicadoEn;

	public Libro() {

	}

	/**
	 * Constructor principal del objeto libro, lo utilizamos para crear libros en InicializarDatos
	 * @param id
	 * @param titulo
	 * @param autor
	 * @param isbn
	 * @param publicadoEn
	 */
	public Libro(Integer id, String titulo, String autor, String isbn, Integer publicadoEn) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.isbn = isbn;
		this.publicadoEn = publicadoEn;
     
		}
/**
 * 
 * GETTERS Y SETTERS
 */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getPublicadoEn() {
		return publicadoEn;
	}

	public void setPublicadoEn(Integer publicadoEn) {
		this.publicadoEn = publicadoEn;
	}

}
