package com.daw.celiblog.db.entity;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * The persistent class for the rol database table.
 * 
 */
@Entity
@NamedQuery(name="Rol.findAll", query="SELECT r FROM Rol r")
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_rol")
	private Long idRol;

	private String nombre;

	public Rol() {
	}

	public Rol(Long idRol, String nombre) {
		this.idRol = idRol;
		this.nombre = nombre;
	}

	public Long getIdRol() {
		return this.idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}




}