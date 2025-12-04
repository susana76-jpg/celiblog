package com.daw.celiblog.db.entity;

import com.daw.celiblog.enums.EstadoValidacionEnum;
import com.daw.celiblog.enums.TipoComidaEnum;
import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

import java.io.Serializable;
import java.util.Date;

@Entity
@Immutable
@Table(name = "vista_recetas")
public class VistaReceta implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id", nullable = false)
	private Long id;
	@Column(name = "id_receta")
	private Long idReceta;
	@Column(name = "id_usuario")
	private Long idUsuario;
	@Column(name = "fecha_creacion")
	private Date fechaCreación;
	private String titulo;
	private String subtitulo;
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_comida")
	private TipoComidaEnum tipoComida;
	private String descripcion;
	private String dificultad;
	private Integer valoracion;
	@Column(name = "tiempo_preparacion")
	private Integer tiempoPreparacion;
	@Column(name = "valor_energetico")
	private Integer valorEnergetico;
	private Integer comensales;
	@Column(name = "imagen_url")
	private String imagenUrl;
	@Enumerated(EnumType.STRING)
	@Column(name = "estado", nullable = false)
	private EstadoValidacionEnum estado;
	@Column(name = "fecha_validacion")
	private Date fechaValidacion;
	@Column(name = "cantidad")
	private Integer cantidad;
	@Column(name = "id_ingrediente")
	private Long idIngrediente;
	@Column(name = "nombre")
	private String nombreIngrediente;
	private String unidad;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Long getIdReceta() {
		return idReceta;
	}

	public void setIdReceta(Long idReceta) {
		this.idReceta = idReceta;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Date getFechaCreación() {
		return fechaCreación;
	}

	public void setFechaCreación(Date fechaCreación) {
		this.fechaCreación = fechaCreación;
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

	public TipoComidaEnum getTipoComida() {
		return tipoComida;
	}

	public void setTipoComida(TipoComidaEnum tipoComida) {
		this.tipoComida = tipoComida;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDificultad() {
		return dificultad;
	}

	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
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

	public int getComensales() {
		return comensales;
	}

	public void setComensales(int comensales) {
		this.comensales = comensales;
	}

	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
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

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Long getIdIngrediente() {
		return idIngrediente;
	}

	public void setIdIngrediente(Long idIngrediente) {
		this.idIngrediente = idIngrediente;
	}

	public String getNombreIngrediente() {
		return nombreIngrediente;
	}

	public void setNombreIngrediente(String nombreIngrediente) {
		this.nombreIngrediente = nombreIngrediente;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
}