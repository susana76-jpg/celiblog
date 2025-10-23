package com.daw.celiblog.db.entity;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * The persistent class for the comentario database table.
 * 
 */
@Entity
@NamedQuery(name="Comentario.findAll", query="SELECT c FROM Comentario c")
public class Comentario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_comentario")
	private Long idComentario;

	@Column(name="comentario_url")
	private String comentarioUrl;

	private String contenido;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	//bi-directional many-to-one association to TipoComentario
	@ManyToOne
	@JoinColumn(name="id_tipo_comentario")
	private TipoComentario tipoComentario;



	public Comentario() {
	}

	public Comentario(Long idComentario, String comentarioUrl, String contenido, Usuario usuario, TipoComentario tipoComentario) {
		this.idComentario = idComentario;
		this.comentarioUrl = comentarioUrl;
		this.contenido = contenido;
		this.usuario = usuario;
		this.tipoComentario = tipoComentario;
	}

	public Long getIdComentario() {
		return this.idComentario;
	}

	public void setIdComentario(Long idComentario) {
		this.idComentario = idComentario;
	}

	public String getComentarioUrl() {
		return this.comentarioUrl;
	}

	public void setComentarioUrl(String comentarioUrl) {
		this.comentarioUrl = comentarioUrl;
	}

	public String getContenido() {
		return this.contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public TipoComentario getTipoComentario() {
		return this.tipoComentario;
	}

	public void setTipoComentario(TipoComentario tipoComentario) {
		this.tipoComentario = tipoComentario;
	}










}