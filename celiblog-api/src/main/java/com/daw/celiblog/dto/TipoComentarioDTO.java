package com.daw.celiblog.dto;

import com.daw.celiblog.enums.ObjetoComentario;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class TipoComentarioDTO {

    private Long idTipoComentario;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private ObjetoComentario tema = ObjetoComentario.PENDIENTE;

    public TipoComentarioDTO() {
    }

    public TipoComentarioDTO(Long idTipoComentario, String nombre, ObjetoComentario tema) {
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

    public ObjetoComentario getTema() {
        return tema;
    }

    public void setTema(ObjetoComentario tema) {
        this.tema = tema;
    }
}
