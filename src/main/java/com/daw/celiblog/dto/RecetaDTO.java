package com.daw.celiblog.dto;

import com.daw.celiblog.db.entity.Usuario;
import com.daw.celiblog.util.EstadoValidacion;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Date;
import java.util.Objects;

public class RecetaDTO {

    private Long idReceta;

    private UsuarioDTO usuario;
    private String descripcion;
    private Date fechaCreacion;
    private String imagenUrl;
    private String dificultad;

    private String titulo;

    @Enumerated(EnumType.STRING)
    private EstadoValidacion estado = EstadoValidacion.APROBADO;

    private Date fechaValidacion;

    public RecetaDTO() {
    }

    public RecetaDTO(Long idReceta, UsuarioDTO usuario, String descripcion, Date fechaCreacion, String imagenUrl, String dificultad, String titulo, EstadoValidacion estado, Date fechaValidacion) {
        this.idReceta = idReceta;
        this.usuario = usuario;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.imagenUrl = imagenUrl;
        this.dificultad = dificultad;
        this.titulo = titulo;
        this.estado = estado;
        this.fechaValidacion = fechaValidacion;
    }

    public Long getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(Long idReceta) {
        this.idReceta = idReceta;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
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
