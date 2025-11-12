package com.daw.celiblog.dto;

import com.daw.celiblog.enums.EstadoValidacion;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ComentarioDTO {
    private Long idComentario;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fechaPublicacion;
    private String contenido;
    private String comentarioUrl;
    //bi-directional many-to-one association to Usuario
    private UsuarioDTO usuarioDTO;
    //bi-directional many-to-one association to TipoComentario
    private TipoComentarioDTO tipoComentarioDTO;
    private Long idObjetoComentado;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fechaValidacion;
    private int  valoracion;
    private EstadoValidacion estado = EstadoValidacion.APROBADO;

    public ComentarioDTO() {
    }

    public ComentarioDTO(Long idComentario, Date fechaPublicacion, String contenido, String comentarioUrl, UsuarioDTO usuarioDTO, TipoComentarioDTO tipoComentarioDTO, Long idObjetoComentado, Date fechaValidacion, int valoracion, EstadoValidacion estado) {
        this.idComentario = idComentario;
        this.fechaPublicacion = fechaPublicacion;
        this.contenido = contenido;
        this.comentarioUrl = comentarioUrl;
        this.usuarioDTO = usuarioDTO;
        this.tipoComentarioDTO = tipoComentarioDTO;
        this.idObjetoComentado = idObjetoComentado;
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

    public TipoComentarioDTO getTipoComentarioDTO() {
        return tipoComentarioDTO;
    }

    public void setTipoComentario(TipoComentarioDTO tipoComentario) {
        this.tipoComentarioDTO = tipoComentario;
    }

    public Long getIdObjetoComentado() {
        return idObjetoComentado;
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

    public EstadoValidacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoValidacion estado) {
        this.estado = estado;
    }
}
