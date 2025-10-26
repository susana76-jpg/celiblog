package com.daw.celiblog.dto;

public class FavoritoView {
    private Long idUsuario;
    private String tipoReferencia;
    private Long idReferencia;

    public FavoritoView() {
    }

    public FavoritoView(Long idUsuario, String tipoReferencia, Long idReferencia) {
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

    public String getTipoReferencia() {
        return tipoReferencia;
    }

    public void setTipoReferencia(String tipoReferencia) {
        this.tipoReferencia = tipoReferencia;
    }

    public Long getIdReferencia() {
        return idReferencia;
    }

    public void setIdReferencia(Long idReferencia) {
        this.idReferencia = idReferencia;
    }
}
