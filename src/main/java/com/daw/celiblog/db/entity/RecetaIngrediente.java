package com.daw.celiblog.db.entity;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * The persistent class for the receta_ingrediente database table.
 * 
 */
@Entity
@Table(name="receta_ingrediente")
@NamedQuery(name="RecetaIngrediente.findAll", query="SELECT r FROM RecetaIngrediente r")
public class RecetaIngrediente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_recetaIngrediente;

	private int cantidad;

	//bi-directional many-to-one association to Ingrediente
	@ManyToOne
	@JoinColumn(name="id_ingrediente")
	private Ingrediente ingrediente;

	//bi-directional many-to-one association to Receta
	@ManyToOne
	@JoinColumn(name="id_receta")
	private Receta receta;

	public RecetaIngrediente() {
	}

	public int getId_recetaIngrediente() {
		return this.id_recetaIngrediente;
	}

	public void setId_recetaIngrediente(int id_recetaIngrediente) {
		this.id_recetaIngrediente = id_recetaIngrediente;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Ingrediente getIngrediente() {
		return this.ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

	public Receta getReceta() {
		return this.receta;
	}

	public void setReceta(Receta receta) {
		this.receta = receta;
	}

}