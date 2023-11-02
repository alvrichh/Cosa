package com.example.libros.entidad;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Libro {
		
		private Long id;
		@NotBlank(message="El autor no puede estar vacío.")
		private String autor;
		@NotBlank(message="El nombre no puede estar vacío.")
		private String nombre;
		@NotNull(message="EL precio no puede estar vacío.")
		private Double precio;
		@NotNull(message="EL año no puede estar vacío.")
		private int anyoPublicacion;
		@NotNull(message="La categoria es requerida.")
		private Categoria categoria;
				
		
		
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
