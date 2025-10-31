package com.daw.celiblog.db.entity;

import com.daw.celiblog.util.EstadoValidacion;
import com.daw.celiblog.util.TemaComentario;
import jakarta.persistence.*;

import java.io.Serializable;

/**
 * The persistent class for the tipo_comentario database table.
 * 
 */
@Entity
@Table(name="tipo_comentario")
@NamedQuery(name="TipoComentario.findAll", query="SELECT t FROM TipoComentario t")
public class TipoComentario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_tipo_comentario")
	private Long idTipoComentario;

	private String nombre;

	@Enumerated(EnumType.STRING)
	@Column(name = "tema", nullable = false)
	private TemaComentario tema = TemaComentario.PENDIENTE;

	public TipoComentario() {
	}

	public TipoComentario(Long idTipoComentario, String nombre, TemaComentario tema) {
		this.idTipoComentario = idTipoComentario;
		this.nombre = nombre;
		this.tema = tema;
	}

	public Long getIdTipoComentario() {
		return this.idTipoComentario;
	}

	public void setIdTipoComentario(Long idTipoComentario) {
		this.idTipoComentario = idTipoComentario;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TemaComentario getTema() {
		return this.tema;
	}

	public void setTema(TemaComentario tema) {
		this.tema = tema;
	}

}