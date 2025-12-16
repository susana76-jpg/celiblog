package com.daw.celiblog.dto;

public class EstadisticaDTO {
    private int numRestaurantes;
    private int numRecetas;
    private int numPost;
    private int numComentarios;

    public EstadisticaDTO() {
    }

    public EstadisticaDTO(int numRestaurantes, int numRecetas, int numPost, int numComentarios) {
        this.numRestaurantes = numRestaurantes;
        this.numRecetas = numRecetas;
        this.numPost = numPost;
        this.numComentarios = numComentarios;
    }

    public int getNumRestaurantes() {
        return numRestaurantes;
    }

    public void setNumRestaurantes(int numRestaurantes) {
        this.numRestaurantes = numRestaurantes;
    }

    public int getNumRecetas() {
        return numRecetas;
    }

    public void setNumRecetas(int numRecetas) {
        this.numRecetas = numRecetas;
    }

    public int getNumPost() {
        return numPost;
    }

    public void setNumPost(int numPost) {
        this.numPost = numPost;
    }

    public int getNumComentarios() {
        return numComentarios;
    }

    public void setNumComentarios(int numComentarios) {
        this.numComentarios = numComentarios;
    }
}
