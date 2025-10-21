package com.daw.celiblog.db.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the post database table.
 * 
 */
@Entity
@NamedQuery(name="Post.findAll", query="SELECT p FROM Post p")
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_post")
	private int idPost;

	private String contenido;

	@Column(name="url_post")
	private String urlPost;

	//bi-directional many-to-one association to ComentarioPost
	@OneToMany(mappedBy="post")
	private List<ComentarioPost> comentarioPosts;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	//bi-directional many-to-one association to TagPost
	@OneToMany(mappedBy="post")
	private List<TagPost> tagPosts;

	public Post() {
	}

	public int getIdPost() {
		return this.idPost;
	}

	public void setIdPost(int idPost) {
		this.idPost = idPost;
	}

	public String getContenido() {
		return this.contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getUrlPost() {
		return this.urlPost;
	}

	public void setUrlPost(String urlPost) {
		this.urlPost = urlPost;
	}

	public List<ComentarioPost> getComentarioPosts() {
		return this.comentarioPosts;
	}

	public void setComentarioPosts(List<ComentarioPost> comentarioPosts) {
		this.comentarioPosts = comentarioPosts;
	}

	public ComentarioPost addComentarioPost(ComentarioPost comentarioPost) {
		getComentarioPosts().add(comentarioPost);
		comentarioPost.setPost(this);

		return comentarioPost;
	}

	public ComentarioPost removeComentarioPost(ComentarioPost comentarioPost) {
		getComentarioPosts().remove(comentarioPost);
		comentarioPost.setPost(null);

		return comentarioPost;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<TagPost> getTagPosts() {
		return this.tagPosts;
	}

	public void setTagPosts(List<TagPost> tagPosts) {
		this.tagPosts = tagPosts;
	}

	public TagPost addTagPost(TagPost tagPost) {
		getTagPosts().add(tagPost);
		tagPost.setPost(this);

		return tagPost;
	}

	public TagPost removeTagPost(TagPost tagPost) {
		getTagPosts().remove(tagPost);
		tagPost.setPost(null);

		return tagPost;
	}

}