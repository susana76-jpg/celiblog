package com.daw.celiblog.db.entity;

import com.daw.celiblog.enums.EstadoValidacionEnum;
import com.daw.celiblog.enums.ObjetoEnum;
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
	private String titulo;
	private String subtitulo;
	private String contenido;

	@Column(name="comentario_url")
	private String comentarioUrl;
	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	//bi-directional many-to-one association to TipoComentario

	@Column(name="id_objeto_comentado")
	private Long idObjetoComentado;

	@Enumerated(EnumType.STRING)
	@Column(name = "objeto_comentado", nullable = false)
	private ObjetoEnum objeto_comentado = ObjetoEnum.PENDIENTE;

	@Column(name = "fecha_validacion")
	private Date fechaValidacion;

	private int  valoracion;

	@Enumerated(EnumType.STRING)
	@Column(name = "estado", nullable = false)
	private EstadoValidacionEnum estado = EstadoValidacionEnum.PENDIENTE;



	public Comentario() {
	}

	public Comentario(Long idComentario, Date fechaPublicacion, String contenido, String titulo,String subtitulo, String comentarioUrl, Usuario usuario, Long idObjetoComentado, ObjetoEnum objetoComentado, Date fechaValidacion, int valoracion, EstadoValidacionEnum estado) {
		this.idComentario = idComentario;
		this.fechaPublicacion = fechaPublicacion;
		this.comentarioUrl = comentarioUrl;
		this.contenido = contenido;
		this.titulo = titulo;
		this.subtitulo = subtitulo;
		this.usuario = usuario;
		this.idObjetoComentado = idObjetoComentado;
		this.objeto_comentado = objetoComentado;
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

	public String getSubtitulo() {
		return subtitulo;
	}

	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getIdObjetoComentado() {
		return idObjetoComentado;
	}

	public void setIdObjetoComentado(Long idObjetoComentado) {
		this.idObjetoComentado = idObjetoComentado;
	}

	public ObjetoEnum getObjeto_comentado() {
		return objeto_comentado;
	}

	public void setObjeto_comentado(ObjetoEnum objeto_comentado) {
		this.objeto_comentado = objeto_comentado;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
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

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}