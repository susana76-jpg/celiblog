package com.daw.celiblog.dto;

public class PasoRecetaView {
    private Long idReceta;
    private String descripcion;
    private int orden;

    public PasoRecetaView() {
    }

    public PasoRecetaView(Long idReceta, String descripcion, int orden) {
        this.idReceta = idReceta;
        this.descripcion = descripcion;
        this.orden = orden;
    }

    public Long getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(Long idReceta) {
        this.idReceta = idReceta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }
}
