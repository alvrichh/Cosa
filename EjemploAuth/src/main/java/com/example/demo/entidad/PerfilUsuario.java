package com.example.demo.entidad;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

@Entity
@Table(name="Perfil_Usuario")
public class PerfilUsuario {
	
	
	@Id
	@GeneratedValue
	private Long Id;
	private String nombre;
	private String apellido;
	@Email
	private String email;
	@OneToOne
	@JoinColumn(name="usuario_id", referencedColumnName = "id")
	private Usuario usuario;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
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
