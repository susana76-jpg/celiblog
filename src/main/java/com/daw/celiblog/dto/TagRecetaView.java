package com.daw.celiblog.dto;

public class TagRecetaView {
    private String nombre;
    private Long idReceta;

    public TagRecetaView() {
    }

    public TagRecetaView(String nombre, Long idReceta) {
        this.nombre = nombre;
        this.idReceta = idReceta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(Long idReceta) {
        this.idReceta = idReceta;
    }
}
