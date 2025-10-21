package com.daw.celiblog.dto;

public class TipoComentarioDTO {

    private Long idTipoComentario;
    private String nombre;
    private String tema;

    public TipoComentarioDTO() {
    }

    public TipoComentarioDTO(Long idTipoComentario, String nombre, String tema) {
        this.idTipoComentario = idTipoComentario;
        this.nombre = nombre;
        this.tema = tema;
    }

    public Long getIdTipoComentario() {
        return idTipoComentario;
    }

    public void setIdTipoComentario(Long idTipoComentario) {
        this.idTipoComentario = idTipoComentario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }
}
