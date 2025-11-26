package com.daw.celiblog.db.entity;

import com.daw.celiblog.enums.EstadoValidacion;
import com.daw.celiblog.enums.TipoComida;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the receta database table.
 * 
 */
@Entity
@NamedQuery(name="Receta.findAll", query="SELECT r FROM Receta r")
public class Receta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_receta")
	private Long idReceta;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	private String descripcion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	@Column(name="imagen_url")
	private String imagenUrl;
	private String titulo;
	private String subtitulo;
	private String dificultad;
	@Enumerated(EnumType.STRING)
	@Column(name = "estado", nullable = false)
	private EstadoValidacion estado = EstadoValidacion.APROBADO;
	@Column(name = "fecha_validacion")
	private Date fechaValidacion;
	private int valoracion;

	private int comensales;
	@Column(name = "tiempo_preparacion")
	private int tiempoPreparacion;
	@Column(name = "valor_energetico")
	private int valorEnergetico;
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_comida", nullable = false)
	private TipoComida tipoComida;



	public Receta() {
	}

	public Receta(Long idReceta, Usuario usuario, String descripcion, Date fechaCreacion, String imagenUrl, String titulo, String subtitulo, String dificultad, EstadoValidacion estado, Date fechaValidacion, int valoracion, int comensales, int tiempoPreparacion, int valorEnergetico, TipoComida tipoComida) {
		this.idReceta = idReceta;
		this.usuario = usuario;
		this.descripcion = descripcion;
		this.fechaCreacion = fechaCreacion;
		this.imagenUrl = imagenUrl;
		this.titulo = titulo;
		this.subtitulo = subtitulo;
		this.dificultad = dificultad;
		this.estado = estado;
		this.fechaValidacion = fechaValidacion;
		this.valoracion = valoracion;
		this.comensales = comensales;
		this.tiempoPreparacion = tiempoPreparacion;
		this.valorEnergetico = valorEnergetico;
		this.tipoComida = tipoComida;
	}

	public Long getIdReceta() {
		return idReceta;
	}

	public void setIdReceta(Long idReceta) {
		this.idReceta = idReceta;
	}



	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
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

	public String getDificultad() {
		return dificultad;
	}

	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public int getComensales() {
		return comensales;
	}

	public void setComensales(int comensales) {
		this.comensales = comensales;
	}

	public int getTiempoPreparacion() {
		return tiempoPreparacion;
	}

	public void setTiempoPreparacion(int tiempoPreparacion) {
		this.tiempoPreparacion = tiempoPreparacion;
	}

	public int getValorEnergetico() {
		return valorEnergetico;
	}

	public void setValorEnergetico(int valorEnergetico) {
		this.valorEnergetico = valorEnergetico;
	}

	public TipoComida getTipoComida() {
		return tipoComida;
	}

	public void setTipoComida(TipoComida tipoComida) {
		this.tipoComida = tipoComida;
	}
}