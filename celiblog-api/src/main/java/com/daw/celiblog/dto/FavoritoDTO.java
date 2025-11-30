package com.daw.celiblog.dto;

import com.daw.celiblog.db.entity.Usuario;
import com.daw.celiblog.enums.ObjetoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;

public class FavoritoDTO {
    private Long idFavorito;
    private UsuarioDTO usuarioDTO;
    private Long idUsuario;
    @Enumerated(EnumType.STRING)
    private ObjetoEnum tipoReferencia;
    private Long idReferencia;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fecha;

    public FavoritoDTO() {
    }
    public FavoritoDTO(Long idFavorito, UsuarioDTO usuarioDTO, Long idUsuario, ObjetoEnum tipoReferencia, Long idReferencia, Date fecha) {
        this.idFavorito = idFavorito;
        this.usuarioDTO = usuarioDTO;
        this.idUsuario = idUsuario;
        this.tipoReferencia = tipoReferencia;
        this.idReferencia = idReferencia;
        this.fecha = fecha;
    }
    public FavoritoDTO(Long idFavorito, Long idUsuario, ObjetoEnum tipoReferencia, Long idReferencia, Date fecha) {
        this.idFavorito = idFavorito;
        this.idUsuario = idUsuario;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
