package com.daw.celiblog.dto;

import com.daw.celiblog.db.entity.Usuario;
import com.daw.celiblog.enums.EstadoValidacionEnum;
import com.daw.celiblog.enums.TipoComidaEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Date;

public class PostDTO {
	private Long idPost;
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private Date fechaPublicacion;
	private String titulo;
	private String subtitulo;
	private String contenido;
	@JsonProperty("usuarioPublicacion")
	private UsuarioSummaryDTO usuario;
	@ManyToOne
	@JsonIgnore
	private UsuarioDTO usuarioDTO;
	private String urlPost;
	@Enumerated(EnumType.STRING)
	private EstadoValidacionEnum estado = EstadoValidacionEnum.PENDIENTE;
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private Date fechaValidacion;
	private int valoracion;
	private boolean esFavoritoUsuario;

	public PostDTO() {
	}

	public PostDTO(Long idPost, Date fechaPublicacion, String titulo, String subtitulo, String contenido, UsuarioSummaryDTO usuario, UsuarioDTO usuarioDTO, String urlPost, EstadoValidacionEnum estado, Date fechaValidacion, int valoracion) {
		this.idPost = idPost;
		this.fechaPublicacion = fechaPublicacion;
		this.titulo = titulo;
		this.subtitulo = subtitulo;
		this.contenido = contenido;
		this.usuario = usuario;
		this.usuarioDTO = usuarioDTO;
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

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public UsuarioSummaryDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioSummaryDTO usuario) {
		this.usuario = usuario;
	}

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
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

	public boolean isEsFavoritoUsuario() {
		return esFavoritoUsuario;
	}

	public void setEsFavoritoUsuario(boolean esFavoritoUsuario) {
		this.esFavoritoUsuario = esFavoritoUsuario;
	}
}
