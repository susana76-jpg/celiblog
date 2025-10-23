package com.daw.celiblog.db.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the comentario_receta database table.
 * 
 */
@Entity
@Table(name="comentario_receta")
@NamedQuery(name="ComentarioReceta.findAll", query="SELECT c FROM ComentarioReceta c")
public class ComentarioReceta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_comentario_receta")
	private int idComentarioReceta;

	private int valoracion;

	//bi-directional many-to-one association to Receta
	@ManyToOne
	@JoinColumn(name="id_receta")
	private Receta receta;

	//bi-directional many-to-one association to Comentario
	@ManyToOne
	@JoinColumn(name="id_comentario")
	private Comentario comentario;
	private Date fecha;

	public ComentarioReceta() {
	}

	public int getIdComentarioReceta() {
		return this.idComentarioReceta;
	}

	public void setIdComentarioReceta(int idComentarioReceta) {
		this.idComentarioReceta = idComentarioReceta;
	}

	public int getValoracion() {
		return this.valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

	public Receta getReceta() {
		return this.receta;
	}

	public void setReceta(Receta receta) {
		this.receta = receta;
	}

	public Comentario getComentario() {
		return this.comentario;
	}

	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}