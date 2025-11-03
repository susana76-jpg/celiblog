package com.daw.celiblog.dto;

public class PasoRecetaDTO {
    private Long idPaso;
    private String descripcion;
    private int orden;
    private RecetaDTO recetaDTO;
    private Long idRecta;

    public PasoRecetaDTO() {
    }

    public PasoRecetaDTO(Long idPaso, String descripcion, int orden, RecetaDTO receta, Long idRecta) {
        this.idPaso = idPaso;
        this.descripcion = descripcion;
        this.orden = orden;
        this.recetaDTO = receta;
        this.idRecta = idRecta;
    }

    public PasoRecetaDTO(Long idPaso, String descripcion, int orden, Long idRecta) {
        this.idPaso = idPaso;
        this.descripcion = descripcion;
        this.orden = orden;
        this.idRecta = idRecta;
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

    public Long getIdRecta() {
        return idRecta;
    }

    public void setIdRecta(Long idRecta) {
        this.idRecta = idRecta;
    }
}
