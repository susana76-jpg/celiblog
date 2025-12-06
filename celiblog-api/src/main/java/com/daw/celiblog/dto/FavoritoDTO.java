package com.daw.celiblog.dto;

import com.daw.celiblog.db.entity.Usuario;
import com.daw.celiblog.enums.ObjetoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Date;

public class FavoritoDTO {
    private Long idFavorito;
    @ManyToOne
    @JsonIgnore
    private UsuarioDTO usuarioDTO;
    @JsonProperty("usuarioPublicacion")
    private UsuarioSummaryDTO usuario;
    @Enumerated(EnumType.STRING)
    private ObjetoEnum tipoReferencia;
    private Long idReferencia;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fecha;

    public FavoritoDTO() {
    }

    public FavoritoDTO(Long idFavorito, UsuarioSummaryDTO usuario, ObjetoEnum tipoReferencia, Long idReferencia, Date fecha) {
        this.idFavorito = idFavorito;
        this.usuario = usuario;
        this.tipoReferencia = tipoReferencia;
        this.idReferencia = idReferencia;
        this.fecha = fecha;
    }

    public Long getIdFavorito() {
        return idFavorito;
    }

    public void setIdFavorito(Long idFavorito) {
        this.idFavorito = idFavorito;
    }

    public UsuarioDTO getUsuario() {
        return usuarioDTO;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuarioDTO = usuario;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }

    public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
    }

    public void getUsuario(UsuarioSummaryDTO usuario) {
        this.usuario = usuario;
    }

    public void setUsuario(UsuarioSummaryDTO usuario) {
        this.usuario = usuario;
    }
}
