package com.daw.celiblog.db.entity;

import jakarta.persistence.*;

import java.io.Serializable;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private Long idUsuario;

	private String email;

	private String nombre;

	private String password;

	//bi-directional many-to-one association to Comentario

	//bi-directional many-to-one association to Rol
	@ManyToOne
	@JoinColumn(name="id_rol")
	private Rol rol;

	public Usuario() {
	}

	public Usuario(Long idUsuario, String email, String nombre, String password, Rol rol) {
		this.idUsuario = idUsuario;
		this.email = email;
		this.nombre = nombre;
		this.password = password;
		this.rol = rol;
	}
	public Usuario(String email, String nombre, String password, Rol rol) {
		this.email = email;
		this.nombre = nombre;
		this.password = password;
		this.rol = rol;
	}

	public Long getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

}