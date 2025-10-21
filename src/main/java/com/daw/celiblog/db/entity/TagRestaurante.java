package com.daw.celiblog.db.entity;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * The persistent class for the tag_restaurante database table.
 * 
 */
@Entity
@Table(name="tag_restaurante")
@NamedQuery(name="TagRestaurante.findAll", query="SELECT t FROM TagRestaurante t")
public class TagRestaurante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_tag")
	private Long idTag;

	private String nombre;

	//bi-directional many-to-one association to Restaurante
	@ManyToOne
	@JoinColumn(name="id_restaurante")
	private Restaurante restaurante;

	public TagRestaurante() {}

	public TagRestaurante(Long idTag, String nombre, Restaurante restaurante) {
		this.idTag = idTag;
		this.nombre = nombre;
		this.restaurante = restaurante;
	}



	public Long getIdTag() {
		return this.idTag;
	}

	public void setIdTag(Long idTag) {
		this.idTag = idTag;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Restaurante getRestaurante() {
		return this.restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

}