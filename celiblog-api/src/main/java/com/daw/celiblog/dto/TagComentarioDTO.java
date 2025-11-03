package com.daw.celiblog.dto;

public class TagComentarioDTO {

    private Long idComentario;

    private String comentarioUrl;

    private String contenido;

    private UsuarioDTO usuario;

    private TipoComentarioDTO tipoComentario;

    public TagComentarioDTO() {
    }

    public TagComentarioDTO(Long idComentario, String comentarioUrl, String contenido, UsuarioDTO usuario, TipoComentarioDTO tipoComentario) {
        this.idComentario = idComentario;
        this.comentarioUrl = comentarioUrl;
        this.contenido = contenido;
        this.usuario = usuario;
        this.tipoComentario = tipoComentario;
    }

    public Long getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(Long idComentario) {
        this.idComentario = idComentario;
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

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public TipoComentarioDTO getTipoComentario() {
        return tipoComentario;
    }

    public void setTipoComentario(TipoComentarioDTO tipoComentario) {
        this.tipoComentario = tipoComentario;
    }
}
