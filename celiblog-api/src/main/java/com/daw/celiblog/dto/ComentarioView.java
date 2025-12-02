package com.daw.celiblog.dto;

import com.daw.celiblog.enums.EstadoValidacionEnum;
import com.daw.celiblog.enums.ObjetoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ComentarioView {
    private String subtitulo;
    private String contenido;
    private String comentarioUrl;
    private Long idObjetoComentado;
    private int valoracion;


    public ComentarioView() {
    }

    public ComentarioView(String subtitulo, String contenido, String comentarioUrl, Long idObjetoComentado, int valoracion) {
        this.subtitulo = subtitulo;
        this.contenido = contenido;
        this.comentarioUrl = comentarioUrl;
        this.idObjetoComentado = idObjetoComentado;
        this.valoracion = valoracion;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
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
    public Long getIdObjetoComentado() {
        return idObjetoComentado;
    }
    public void setIdObjetoComentado(Long idObjetoComentado) {
        this.idObjetoComentado = idObjetoComentado;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }
}
