package com.daw.celiblog.db.entity;

import com.daw.celiblog.util.EstadoValidacion;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_post")
	private Long idPost;
	@Column(name="fecha_publicacion")
	private Date fechaPublicacion;
	//bi-directional many-to-one association to Usuario
	private String contenido;
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	@Column(name="url_post")
	private String urlPost;
	@Enumerated(EnumType.STRING)
	@Column(name = "estado", nullable = false)
	private EstadoValidacion estado = EstadoValidacion.PENDIENTE;
	@Column(name = "fecha_validacion")
	private Date fechaValidacion;
	private int valoracion;

	public Post() {
	}

	public Post(Long idPost, Date fechaPublicacion, String contenido, Usuario usuario, String urlPost, EstadoValidacion estado, Date fechaValidacion, int valoracion) {
		this.idPost = idPost;
		this.fechaPublicacion = fechaPublicacion;
		this.contenido = contenido;
		this.usuario = usuario;
		this.urlPost = urlPost;
		this.estado = estado;
		this.fechaValidacion = fechaValidacion;
		this.valoracion = valoracion;
	}

	public Long getIdPost() {
		return idPost;
	}

	public void setIdPost(Long idPost) {
		this.idPost = idPost;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getUrlPost() {
		return urlPost;
	}

	public void setUrlPost(String urlPost) {
		this.urlPost = urlPost;
	}

	public EstadoValidacion getEstado() {
		return estado;
	}

	public void setEstado(EstadoValidacion estado) {
		this.estado = estado;
	}

	public Date getFechaValidacion() {
		return fechaValidacion;
	}

	public void setFechaValidacion(Date fechaValidacion) {
		this.fechaValidacion = fechaValidacion;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}
}