package com.daw.celiblog.dto;

public class IngredienteView {
    private Long idReceta;
    private String nombre;
    private String unidad;

    //receta-ingrediente
    private int cantidad;

    public IngredienteView() {
    }

    public IngredienteView(Long idReceta, String nombre, String unidad, int cantidad) {
        this.idReceta = idReceta;
        this.nombre = nombre;
        this.unidad = unidad;
        this.cantidad = cantidad;
    }

    public Long getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(Long idReceta) {
        this.idReceta = idReceta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
