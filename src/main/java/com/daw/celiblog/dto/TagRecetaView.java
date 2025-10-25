package com.daw.celiblog.dto;

public class TagRecetaView {
    private String descripcionTag;
    private Long idReceta;

    public TagRecetaView() {
    }

    public TagRecetaView(String descripcionTag, Long idReceta) {
        this.descripcionTag = descripcionTag;
        this.idReceta = idReceta;
    }

    public String getDescripcionTag() {
        return descripcionTag;
    }

    public void setDescripcionTag(String descripcionTag) {
        this.descripcionTag = descripcionTag;
    }

    public Long getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(Long idReceta) {
        this.idReceta = idReceta;
    }
}
