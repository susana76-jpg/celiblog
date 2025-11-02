package com.daw.celiblog.db.entity;

import com.daw.celiblog.enums.EstadoValidacion;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

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

	@Column(name="fecha_publicacion")
	private Date fechaPublicacion;
	private String contenido;

	@Column(name="comentario_url")
	private String comentarioUrl;
	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	//bi-directional many-to-one association to TipoComentario
	@ManyToOne
	@JoinColumn(name="id_tipo_comentario")
	private TipoComentario tipoComentario;

	@Column(name="id_objeto_comentado")
	private Long idObjetoComentado;
	@Column(name = "fecha_validacion")
	private Date fechaValidacion;

	private int  valoracion;

	@Enumerated(EnumType.STRING)
	@Column(name = "estado", nullable = false)
	private EstadoValidacion estado = EstadoValidacion.APROBADO;



	public Comentario() {
	}

	public Comentario(Long idComentario, Date fechaPublicacion, String contenido, String comentarioUrl, Usuario usuario, TipoComentario tipoComentario, Long idObjetoComentado, Date fechaValidacion, int valoracion, EstadoValidacion estado) {
		this.idComentario = idComentario;
		this.fechaPublicacion = fechaPublicacion;
		this.comentarioUrl = comentarioUrl;
		this.contenido = contenido;
		this.usuario = usuario;
		this.tipoComentario = tipoComentario;
		this.idObjetoComentado = idObjetoComentado;
		this.valoracion = valoracion;
		this.estado = estado;
		this.fechaValidacion = fechaValidacion;
	}

	public Long getIdComentario() {
		return idComentario;
	}

	public void setIdComentario(Long idComentario) {
		this.idComentario = idComentario;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public String getComentarioUrl() {
		return comentarioUrl;
	}

	public void setComentarioUrl(String comentarioUrl) {
		this.comentarioUrl = comentarioUrl;
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

	public TipoComentario getTipoComentario() {
		return tipoComentario;
	}

	public void setTipoComentario(TipoComentario tipoComentario) {
		this.tipoComentario = tipoComentario;
	}

	public Long getIdObjetoComentado() {
		return idObjetoComentado;
	}

	public void setIdObjetoComentado(Long idObjetoComentado) {
		this.idObjetoComentado = idObjetoComentado;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
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
}