package com.daw.celiblog.db.entity;

import com.daw.celiblog.enums.EstadoValidacionEnum;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the restaurante database table.
 * 
 */
@Entity
@NamedQuery(name="Restaurante.findAll", query="SELECT r FROM Restaurante r")
public class Restaurante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_restaurante")
	private Long idRestaurante;
	@Column(name="fecha_publicacion")
	private Date fechaPublicacion;
	private String nombre;
	private String direccion;
	private String titulo;
	private String subtitulo;
	private String descripcion;
	@Column(name="url_web")
	private String urlWeb;
	@Column(name="imagen_url")
	private String imagenUrl;
	private String ubicacion;
	@Column(name="codigo_postal")
	private int codigoPostal;
	private Double latitud;
	private Double longitud;

	private String telefono;
	private String email;
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	@Enumerated(EnumType.STRING)
	@Column(name = "estado", nullable = false)
	private EstadoValidacionEnum estado = EstadoValidacionEnum.PENDIENTE;
	@Column(name = "fecha_validacion")
	private Date fechaValidacion;
	private int valoracion;




	public Restaurante() {
	}

	public Restaurante(Long idRestaurante, Date fechaPublicacion, String nombre, String direccion, String titulo, String subtitulo, String descripcion, String urlWeb, String imagenUrl, String ubicacion, int codigoPostal, Double latitud, Double longitud, String telefono, String email, Usuario usuario, EstadoValidacionEnum estado, Date fechaValidacion, int valoracion) {
		this.idRestaurante = idRestaurante;
		this.fechaPublicacion = fechaPublicacion;
		this.nombre = nombre;
		this.direccion = direccion;
		this.titulo = titulo;
		this.subtitulo = subtitulo;
		this.descripcion = descripcion;
		this.urlWeb = urlWeb;
		this.imagenUrl = imagenUrl;
		this.ubicacion = ubicacion;
		this.codigoPostal = codigoPostal;
		this.latitud = latitud;
		this.longitud = longitud;
		this.telefono = telefono;
		this.email = email;
		this.usuario = usuario;
		this.estado = estado;
		this.fechaValidacion = fechaValidacion;
		this.valoracion = valoracion;
	}

	public Long getIdRestaurante() {
		return this.idRestaurante;
	}

	public void setIdRestaurante(Long idRestaurante) {
		this.idRestaurante = idRestaurante;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getImagenUrl() {
		return this.imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbicacion() {
		return this.ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public int getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public String getUrlWeb() {
		return this.urlWeb;
	}

	public void setUrlWeb(String urlWeb) {
		this.urlWeb = urlWeb;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public String getSubtitulo() {
		return subtitulo;
	}

	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}
}