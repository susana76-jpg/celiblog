package com.daw.celiblog.db.entity;

import com.daw.celiblog.enums.EstadoValidacionEnum;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

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

	private String titulo;
	private String subtitulo;
	private String contenido;
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	@Column(name="url_post")
	private String urlPost;
	@Enumerated(EnumType.STRING)
	@Column(name = "estado", nullable = false)
	private EstadoValidacionEnum estado = EstadoValidacionEnum.PENDIENTE;
	@Column(name = "fecha_validacion")
	private Date fechaValidacion;
	private int valoracion;

	public Post() {
	}

	public Post(Long idPost, Date fechaPublicacion, String contenido, String titulo, String subtitulo, Usuario usuario, String urlPost, EstadoValidacionEnum estado, Date fechaValidacion, int valoracion) {
		this.idPost = idPost;
		this.fechaPublicacion = fechaPublicacion;
		this.titulo = titulo;
		this.subtitulo = subtitulo;
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

	public EstadoValidacionEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoValidacionEnum estado) {
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

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSubtitulo() {
		return subtitulo;
	}

	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}
}