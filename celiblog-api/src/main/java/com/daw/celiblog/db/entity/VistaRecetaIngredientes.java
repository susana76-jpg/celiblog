package com.daw.celiblog.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="vista_receta_ingredientes")
public class VistaRecetaIngredientes {
    @Id
    @Column(name="id_receta_ingrediente")
    private Long idRecetaIngrediente;
    @Column(name="id_receta")
    private Long idReceta;
    @Column(name="id_ingrediente")
    private Long idIngrediente;
    private String nombre;
    private int cantidad;
    private String unidad;

    public Long getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(Long idReceta) {
        this.idReceta = idReceta;
    }

    public Long getIdRecetaIngrediente() {
        return idRecetaIngrediente;
    }

    public void setIdRecetaIngrediente(Long idRecetaIngrediente) {
        this.idRecetaIngrediente = idRecetaIngrediente;
    }

    public Long getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(Long idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }
}
