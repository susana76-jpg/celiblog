package com.daw.celiblog.dto;

import java.util.Date;
import java.util.Objects;

public class RecetaDTO {

    private Long idReceta;
    private String descripcion;
    private Date fechaCreacion;
    private String imagenUrl;
    private String dificultad;

    private String titulo;
   /* private List<ComentarioReceta> comentarioRecetas;
    private List<PasoReceta> pasoRecetas;
    private List<RecetaIngrediente> recetaIngredientes;
    private List<TagReceta> tagRecetas;*/

    public RecetaDTO() {
    }

    public RecetaDTO(Long idReceta, String descripcion, Date fechaCreacion, String imagenUrl, String titulo, String dificultad) {
        this.idReceta = idReceta;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.imagenUrl = imagenUrl;
        this.titulo = titulo;
        this.dificultad = dificultad;
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

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecetaDTO that = (RecetaDTO) o;
        return Objects.equals(idReceta, that.idReceta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReceta);
    }
}
