package com.daw.celiblog.dto;

public class TagRecetaDTO {
    private Long idTag;
    private String nombre;
    private RecetaDTO recetaDTO;
    private Long idReceta;

    public TagRecetaDTO() {
    }

    public TagRecetaDTO(Long idTag, String nombre, RecetaDTO recetaDTO, Long idReceta) {
        this.idTag = idTag;
        this.nombre = nombre;
        this.recetaDTO = recetaDTO;
        this.idReceta = idReceta;
    }

    public TagRecetaDTO(Long idTag, String nombre, Long idReceta) {
        this.idTag = idTag;
        this.nombre = nombre;
        this.idReceta = idReceta;
    }

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

       public Long getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(Long idReceta) {
        this.idReceta = idReceta;
    }

    public RecetaDTO getRecetaDTO() {
        return recetaDTO;
    }

    public void setRecetaDTO(RecetaDTO recetaDTO) {
        this.recetaDTO = recetaDTO;
    }
}
