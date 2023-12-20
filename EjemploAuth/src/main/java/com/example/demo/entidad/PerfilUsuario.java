package com.example.demo.entidad;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="Perfil_Usuario")
public class PerfilUsuario {
	
	
	@Id
	@GeneratedValue
	private Long id;
	@NotBlank(message = "{error.nombre}") // validamos que el campo no esté vacío
	private String nombre;
	@NotBlank(message = "{error.apellido}") // validamos que el campo no esté vacío
	private String apellido;
	@Email
	private String email;
	@OneToOne
	@JoinColumn(name="usuario_id", referencedColumnName = "id")
	private Usuario usuario;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
