package com.daw.celiblog.dto;

import com.daw.celiblog.util.TemaComentario;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class TipoComentarioDTO {

    private Long idTipoComentario;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private TemaComentario tema = TemaComentario.PENDIENTE;

    public TipoComentarioDTO() {
    }

    public TipoComentarioDTO(Long idTipoComentario, String nombre, TemaComentario tema) {
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

    public TemaComentario getTema() {
        return tema;
    }

    public void setTema(TemaComentario tema) {
        this.tema = tema;
    }
}
