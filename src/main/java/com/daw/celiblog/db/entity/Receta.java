package com.daw.celiblog.db.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the receta database table.
 * 
 */
@Entity
@NamedQuery(name="Receta.findAll", query="SELECT r FROM Receta r")
public class Receta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_receta")
	private Long idReceta;

	private String descripcion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="imagen_url")
	private String imagenUrl;

	private String titulo;

	private String dificultad;

	public Receta() {
	}

	public Receta(Long idReceta, String descripcion, Date fechaCreacion, String imagenUrl, String titulo, String dificultad) {
		this.idReceta = idReceta;
		this.descripcion = descripcion;
		this.fechaCreacion = fechaCreacion;
		this.imagenUrl = imagenUrl;
		this.titulo = titulo;
		this.dificultad = dificultad;
	}



	public Long getIdReceta() {
		return idReceta;
	}

	public void setIdReceta(Long idReceta) {
		this.idReceta = idReceta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDificultad() {
		return dificultad;
	}

	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}
}