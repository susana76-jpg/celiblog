package com.daw.celiblog.dto;

public class TagRestauranteDTO {

    private Long idTag;

    private String nombre;

    private RestauranteDTO restaurante;
    private Long idRestaurante;

    public TagRestauranteDTO(Long idTag, String nombre, RestauranteDTO restaurante) {
        this.idTag = idTag;
        this.nombre = nombre;
        this.restaurante = restaurante;

    }
    public TagRestauranteDTO(Long idTag, String nombre,Long idRestaurante) {
        this.idTag = idTag;
        this.nombre = nombre;
        this.idRestaurante = idRestaurante;
    }
    public TagRestauranteDTO(String nombre,Long idRestaurante) {
        this.nombre = nombre;
        this.idRestaurante = idRestaurante;
    }

    public TagRestauranteDTO() {}

    public Long getIdTag() {
        return idTag;
    }

    public void setIdTag(Long idTag) {
        this.idTag = idTag;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public RestauranteDTO getRestaurante() {
        return restaurante;
    }

    public Long getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(Long idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public void setRestaurante(RestauranteDTO restaurante) {
        this.restaurante = restaurante;
    }
}
