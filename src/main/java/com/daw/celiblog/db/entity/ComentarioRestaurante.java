package com.daw.celiblog.db.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the comentario_restaurante database table.
 * 
 */
@Entity
@Table(name="comentario_restaurante")
@NamedQuery(name="ComentarioRestaurante.findAll", query="SELECT c FROM ComentarioRestaurante c")
public class ComentarioRestaurante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_comentario_restaurante")
	private Long idComentarioRestaurante;

	private int valoracion;

	//bi-directional many-to-one association to Comentario
	@ManyToOne
	@JoinColumn(name="id_comentario")
	private Comentario comentario;

	//bi-directional many-to-one association to Restaurante
	@ManyToOne
	@JoinColumn(name="id_restaurante")
	private Restaurante restaurante;
	private Date fecha;

	public ComentarioRestaurante() {}

	public ComentarioRestaurante(Long idComentarioRestaurante, int valoracion, Comentario comentario, Restaurante restaurante, Date fecha) {
		this.idComentarioRestaurante = idComentarioRestaurante;
		this.valoracion = valoracion;
		this.comentario = comentario;
		this.restaurante = restaurante;
		this.fecha = fecha;
	}

	public Long getIdComentarioRestaurante() {
		return this.idComentarioRestaurante;
	}

	public void setIdComentarioRestaurante(Long idComentarioRestaurante) {
		this.idComentarioRestaurante = idComentarioRestaurante;
	}

	public int getValoracion() {
		return this.valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

	public Comentario getComentario() {
		return this.comentario;
	}

	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}

	public Restaurante getRestaurante() {
		return this.restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}