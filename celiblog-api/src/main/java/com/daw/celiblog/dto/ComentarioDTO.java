package com.daw.celiblog.dto;

import com.daw.celiblog.enums.EstadoValidacionEnum;
import com.daw.celiblog.enums.ObjetoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.ManyToOne;

import java.util.Date;

public class ComentarioDTO {
    private Long idComentario;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fechaPublicacion;
    private String contenido;
    private String subtitulo;
    private String comentarioUrl;

    @JsonProperty("usuarioPublicacion")
    private UsuarioSummaryDTO usuario;
    @ManyToOne
    @JsonIgnore
    private UsuarioDTO usuarioDTO;
    private Long idObjetoComentado;

    public ObjetoEnum objetoComentado;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fechaValidacion;
    private int  valoracion;
    private EstadoValidacionEnum estado = EstadoValidacionEnum.APROBADO;

    public ComentarioDTO() {
    }

    public ComentarioDTO(Long idComentario, Date fechaPublicacion, String contenido, String subtitulo, String comentarioUrl, UsuarioSummaryDTO usuarioSummaryDTO, Long idObjetoComentado, ObjetoEnum objetoComentado, Date fechaValidacion, int valoracion, EstadoValidacionEnum estado) {
        this.idComentario = idComentario;
        this.fechaPublicacion = fechaPublicacion;
        this.contenido = contenido;
        this.subtitulo= subtitulo;
        this.comentarioUrl = comentarioUrl;
        this.usuario = usuarioSummaryDTO;
        this.idObjetoComentado = idObjetoComentado;
        this.objetoComentado = objetoComentado;
        this.fechaValidacion = fechaValidacion;
        this.valoracion = valoracion;
        this.estado = estado;
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

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getComentarioUrl() {
        return comentarioUrl;
    }

    public void setComentarioUrl(String comentarioUrl) {
        this.comentarioUrl = comentarioUrl;
    }

    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }

    public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
    }

    public Long getIdObjetoComentado() {
        return idObjetoComentado;
    }

    public ObjetoEnum getObjetoComentado() {
        return objetoComentado;
    }

    public void setObjetoComentado(ObjetoEnum objetoComentado) {
        this.objetoComentado = objetoComentado;
    }

    public void setIdObjetoComentado(Long idObjetoComentado) {
        this.idObjetoComentado = idObjetoComentado;
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

    public EstadoValidacionEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoValidacionEnum estado) {
        this.estado = estado;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public UsuarioSummaryDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioSummaryDTO usuario) {
        this.usuario = usuario;
    }
}
