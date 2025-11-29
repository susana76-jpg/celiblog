package com.daw.celiblog.dto;


import com.daw.celiblog.enums.EstadoValidacionEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Date;

public class PostDTO {

    private Long idPost;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fechaPublicacion;
    //bi-directional many-to-one association to Usuario
    private String contenido;

    private UsuarioDTO usuarioDTO;
    private String urlPost;
    @Enumerated(EnumType.STRING)
    private EstadoValidacionEnum estado = EstadoValidacionEnum.PENDIENTE;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fechaValidacion;
    private int valoracion;

    public PostDTO() {
    }

    public PostDTO(Long idPost, Date fechaPublicacion, String contenido, UsuarioDTO usuarioDTO, String urlPost, EstadoValidacionEnum estado, Date fechaValidacion, int valoracion) {
        this.idPost = idPost;
        this.fechaPublicacion = fechaPublicacion;
        this.contenido = contenido;
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

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
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
}
