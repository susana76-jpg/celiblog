package com.daw.celiblog.db.entity;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * The persistent class for the paso_receta database table.
 * 
 */
@Entity
@Table(name="paso_receta")
@NamedQuery(name="PasoReceta.findAll", query="SELECT p FROM PasoReceta p")
public class PasoReceta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_paso")
	private Long idPaso;
	private String descripcion;
	private int orden;
	@ManyToOne
	@JoinColumn(name="id_receta")
	private Receta receta;
	public PasoReceta() {
	}

	public PasoReceta(Long idPaso, String descripcion, int orden, Receta receta) {
		this.idPaso = idPaso;
		this.descripcion = descripcion;
		this.orden = orden;
		this.receta = receta;
	}
	public PasoReceta(String descripcion, int orden, Receta receta) {
		this.descripcion = descripcion;
		this.orden = orden;
		this.receta = receta;
	}

	public Long getIdPaso() {
		return idPaso;
	}

	public void setIdPaso(Long idPaso) {
		this.idPaso = idPaso;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public Receta getReceta() {
		return receta;
	}

	public void setReceta(Receta receta) {
		this.receta = receta;
	}
}