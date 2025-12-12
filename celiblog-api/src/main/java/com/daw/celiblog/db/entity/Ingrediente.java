package com.daw.celiblog.db.entity;

import jakarta.persistence.*;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_ingrediente")
	private Long idIngrediente;
	private String nombre;
	private String unidad;
	public Ingrediente() {
	}

	public Ingrediente(Long id_Ingrediente, String nombre, String unidad) {
		this.idIngrediente = id_Ingrediente;
		this.nombre = nombre;
		this.unidad = unidad;
	}

	public Long getId_Ingrediente() {
		return idIngrediente;
	}

	public void setId_Ingrediente(Long id_Ingrediente) {
		this.idIngrediente = id_Ingrediente;
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