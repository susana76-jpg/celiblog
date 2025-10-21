package com.daw.celiblog.db.entity;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * The persistent class for the tag_post database table.
 * 
 */
@Entity
@Table(name="tag_post")
@NamedQuery(name="TagPost.findAll", query="SELECT t FROM TagPost t")
public class TagPost implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_tag_post")
	private int idTagPost;

	private String nombre;

	//bi-directional many-to-one association to Post
	@ManyToOne
	@JoinColumn(name="id_post")
	private Post post;

	public TagPost() {
	}

	public int getIdTagPost() {
		return this.idTagPost;
	}

	public void setIdTagPost(int idTagPost) {
		this.idTagPost = idTagPost;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Post getPost() {
		return this.post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

}