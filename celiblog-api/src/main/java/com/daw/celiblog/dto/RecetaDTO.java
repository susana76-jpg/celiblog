package com.daw.celiblog.dto;

import com.daw.celiblog.enums.EstadoValidacionEnum;
import com.daw.celiblog.enums.TipoComidaEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;

import java.util.Date;
import java.util.Objects;

public class RecetaDTO {

    private Long idReceta;
    @JsonProperty("usuarioPublicacion")
    private UsuarioSummaryDTO usuario;
    @ManyToOne
    @JsonIgnore
    private UsuarioDTO usuarioDTO;
    private String descripcion;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fechaCreacion;
    private String imagenUrl;
    private String dificultad;
    private String titulo;
    private String subtitulo;
    @Enumerated(EnumType.STRING)
    private EstadoValidacionEnum estado = EstadoValidacionEnum.APROBADO;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fechaValidacion;
    private int valoracion;
    private int comensales;
    private int tiempoPreparacion;
    private int valorEnergetico;
    private TipoComidaEnum tipoComida;
    private boolean esFavoritoUsuario;


    public RecetaDTO() {
    }

    public RecetaDTO(Long idReceta, UsuarioSummaryDTO usuarioSummaryDTO, String descripcion, Date fechaCreacion, String imagenUrl, String titulo, String subtitulo, String dificultad, EstadoValidacionEnum estado, Date fechaValidacion, int valoracion, int comensales, int tiempoPreparacion, int valorEnergetico, TipoComidaEnum tipoComida) {
        this.idReceta = idReceta;
        this.usuario = usuarioSummaryDTO;
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

    public UsuarioSummaryDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioSummaryDTO usuario) {
        this.usuario = usuario;
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

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
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

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
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

    public TipoComidaEnum getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(TipoComidaEnum tipoComida) {
        this.tipoComida = tipoComida;
    }

    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }

    public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
    }

    public boolean isEsFavoritoUsuario() {
        return esFavoritoUsuario;
    }

    public void setEsFavoritoUsuario(boolean esFavoritoUsuario) {
        this.esFavoritoUsuario = esFavoritoUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecetaDTO that = (RecetaDTO) o;
        return Objects.equals(idReceta, that.idReceta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReceta);
    }

}
