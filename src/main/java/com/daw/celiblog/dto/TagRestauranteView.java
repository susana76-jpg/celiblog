package com.daw.celiblog.dto;

public class TagRestauranteView {
    private String nombre;
    private Long idRestaurante;

    public TagRestauranteView() {
    }

    public TagRestauranteView(String nombre, Long idRestaurante) {
        this.nombre = nombre;
        this.idRestaurante = idRestaurante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(Long idRestaurante) {
        this.idRestaurante = idRestaurante;
    }
}
