package com.daw.celiblog.dto;

import jakarta.validation.constraints.Pattern;


public class PostView {
    private String titulo;
    private String subtitulo;
    private String contenido;
    private String urlPost;
    private int valoracion;

    public PostView() {
    }

    public PostView(String titulo, String subtitulo, String contenido, String urlPost, int valoracion) {
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.contenido = contenido;
        this.urlPost = urlPost;
        this.valoracion = valoracion;
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

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getUrlPost() {
        return urlPost;
    }

    public void setUrlPost(String urlPost) {
        this.urlPost = urlPost;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }
}
