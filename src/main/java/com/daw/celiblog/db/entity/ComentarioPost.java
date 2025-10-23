package com.daw.celiblog.db.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the comentario_post database table.
 * 
 */
@Entity
@Table(name="comentario_post")
@NamedQuery(name="ComentarioPost.findAll", query="SELECT c FROM ComentarioPost c")
public class ComentarioPost implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_comentario_post")
	private int idComentarioPost;

	private int valoracion;

	//bi-directional many-to-one association to Comentario
	@ManyToOne
	@JoinColumn(name="id_comentario")
	private Comentario comentario;

	//bi-directional many-to-one association to Post
	@ManyToOne
	@JoinColumn(name="id_post")
	private Post post;
	private Date fecha;

	public ComentarioPost() {
	}

	public int getIdComentarioPost() {
		return this.idComentarioPost;
	}

	public void setIdComentarioPost(int idComentarioPost) {
		this.idComentarioPost = idComentarioPost;
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

	public Post getPost() {
		return this.post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}