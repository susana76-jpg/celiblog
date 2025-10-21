package com.daw.celiblog.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

import java.io.Serializable;

/**
 * The persistent class for the restaurante database table.
 * 
 */
@Entity
@NamedQuery(name="Restaurante.findAll", query="SELECT r FROM Restaurante r")
public class Restaurante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_restaurante")
	private Long idRestaurante;

	private String descripcion;

	private String direccion;

	@Column(name="imagen_url")
	private String imagenUrl;

	private String nombre;

	private String ubicacion;

	@Column(name="url_web")
	private String urlWeb;

	//bi-directional many-to-one association to ComentarioRestaurante
	/*@OneToMany(mappedBy="restaurante")
	private List<ComentarioRestaurante> comentarioRestaurantes;

	//bi-directional many-to-one association to TagRestaurante
	@OneToMany(mappedBy="restaurante")
	private List<TagRestaurante> tagRestaurantes;*/

	public Restaurante() {
	}

	public Restaurante(Long idRestaurante, String descripcion, String direccion, String imagenUrl, String nombre, String ubicacion, String urlWeb) {
		this.idRestaurante = idRestaurante;
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.imagenUrl = imagenUrl;
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.urlWeb = urlWeb;
	}

	public Long getIdRestaurante() {
		return this.idRestaurante;
	}

	public void setIdRestaurante(Long idRestaurante) {
		this.idRestaurante = idRestaurante;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getImagenUrl() {
		return this.imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbicacion() {
		return this.ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getUrlWeb() {
		return this.urlWeb;
	}

	public void setUrlWeb(String urlWeb) {
		this.urlWeb = urlWeb;
	}






}