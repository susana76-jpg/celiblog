package com.daw.celiblog.db.entity;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * The persistent class for the tag_receta database table.
 * 
 */
@Entity
@Table(name="tag_receta")
@NamedQuery(name="TagReceta.findAll", query="SELECT t FROM TagReceta t")
public class TagReceta implements Serializable {
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_tag")
	private Long idTag;

	private String nombre;

	//bi-directional many-to-one association to Receta
	@ManyToOne
	@JoinColumn(name="id_receta")
	private Receta receta;

	public TagReceta() {
	}

	public TagReceta(Long idTag, String nombre, Receta receta) {
		this.idTag = idTag;
		this.nombre = nombre;
		this.receta = receta;
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

	public Receta getReceta() {
		return this.receta;
	}

	public void setReceta(Receta receta) {
		this.receta = receta;
	}

}