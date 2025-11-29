package com.daw.celiblog.dto;

import com.daw.celiblog.enums.ObjetoEnum;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class FavoritoView {
    private Long idUsuario;
    @Enumerated(EnumType.STRING)
    private ObjetoEnum tipoReferencia;
    private Long idReferencia;

    public FavoritoView() {
    }

    public FavoritoView(Long idUsuario, ObjetoEnum tipoReferencia, Long idReferencia) {
        this.idUsuario = idUsuario;
        this.tipoReferencia = tipoReferencia;
        this.idReferencia = idReferencia;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public ObjetoEnum getTipoReferencia() {
        return tipoReferencia;
    }

    public void setTipoReferencia(ObjetoEnum tipoReferencia) {
        this.tipoReferencia = tipoReferencia;
    }

    public Long getIdReferencia() {
        return idReferencia;
    }

    public void setIdReferencia(Long idReferencia) {
        this.idReferencia = idReferencia;
    }
}
