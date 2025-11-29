package com.daw.celiblog.dto;

import com.daw.celiblog.enums.ObjetoEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class TipoComentarioDTO {

    private Long idTipoComentario;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private ObjetoEnum tema = ObjetoEnum.PENDIENTE;

    public TipoComentarioDTO() {
    }

    public TipoComentarioDTO(Long idTipoComentario, String nombre, ObjetoEnum tema) {
        this.idTipoComentario = idTipoComentario;
        this.nombre = nombre;
        this.tema = tema;
    }

    public Long getIdTipoComentario() {
        return idTipoComentario;
    }

    public void setIdTipoComentario(Long idTipoComentario) {
        this.idTipoComentario = idTipoComentario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ObjetoEnum getTema() {
        return tema;
    }

    public void setTema(ObjetoEnum tema) {
        this.tema = tema;
    }
}
