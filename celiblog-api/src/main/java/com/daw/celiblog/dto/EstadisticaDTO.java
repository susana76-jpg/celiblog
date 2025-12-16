package com.daw.celiblog.dto;

public class EstadisticaDTO {
    private int numRestaurantes;
    private int numRecetas;
    private int numPost;
    private int numComentarios;
    private int numUsuarios;

    public EstadisticaDTO() {
    }

    public EstadisticaDTO(int numRestaurantes, int numRecetas, int numPost, int numComentarios, int numUsuarios) {
        this.numRestaurantes = numRestaurantes;
        this.numRecetas = numRecetas;
        this.numPost = numPost;
        this.numComentarios = numComentarios;
        this.numUsuarios = numUsuarios;
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

    public int getNumUsuarios() {
        return numUsuarios;
    }

    public void setNumUsuarios(int numUsuarios) {
        this.numUsuarios = numUsuarios;
    }
}
