package com.daw.celiblog.dto;

public class PasoRecetaDTO {
    private Long idPaso;
    private String descripcion;
    private int orden;
    private RecetaDTO recetaDTO;
    private Long idReceta;
    public PasoRecetaDTO() {
    }

    public PasoRecetaDTO(Long idPaso, String descripcion, int orden, RecetaDTO receta, Long idReceta) {
        this.idPaso = idPaso;
        this.descripcion = descripcion;
        this.orden = orden;
        this.recetaDTO = receta;
        this.idReceta = idReceta;
    }

    public PasoRecetaDTO(Long idPaso, String descripcion, int orden, Long idReceta) {
        this.idPaso = idPaso;
        this.descripcion = descripcion;
        this.orden = orden;
        this.idReceta = idReceta;
    }

    public Long getIdPaso() {
        return idPaso;
    }

    public void setIdPaso(Long idPaso) {
        this.idPaso = idPaso;
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

    public RecetaDTO getReceta() {
        return recetaDTO;
    }

    public void setReceta(RecetaDTO receta) {
        this.recetaDTO = receta;
    }

    public Long getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(Long idReceta) {
        this.idReceta = idReceta;
    }
}
