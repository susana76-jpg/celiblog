package com.daw.celiblog.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

import java.io.Serializable;
/**
 * The persistent class for the ingrediente database table.
 * 
 */
@Entity
@NamedQuery(name="Ingrediente.findAll", query="SELECT i FROM Ingrediente i")
public class Ingrediente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_ingrediente")
	private Long id_Ingrediente;

	private String nombre;

	private String unidad;

	public Ingrediente() {
	}

	public Ingrediente(Long id_Ingrediente, String nombre, String unidad) {
		this.id_Ingrediente = id_Ingrediente;
		this.nombre = nombre;
		this.unidad = unidad;
	}

	public Long getId_Ingrediente() {
		return id_Ingrediente;
	}

	public void setId_Ingrediente(Long id_Ingrediente) {
		this.id_Ingrediente = id_Ingrediente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
}