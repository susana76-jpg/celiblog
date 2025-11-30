package com.daw.celiblog.db.entity;


import com.daw.celiblog.enums.EstadoValidacionEnum;
import com.daw.celiblog.enums.ObjetoEnum;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="favoritos")
public class Favorito implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_favorito", nullable = false)
    private Long idFavorito;
    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_referencia", nullable = false)
    private ObjetoEnum tipoReferencia;
    @Column(name = "id_referencia")
    private Long idReferencia;
    private Date fecha;
    public Favorito() {
    }

    public Favorito(Long idFavorito, Usuario usuario, ObjetoEnum tipoReferencia, Long idReferencia, Date fecha) {
        this.idFavorito = idFavorito;
        this.usuario = usuario;
        this.tipoReferencia = tipoReferencia;
        this.idReferencia = idReferencia;
        this.fecha = fecha;
    }

    public Favorito(Usuario usuario, ObjetoEnum tipoReferencia, Long idReferencia, Date fecha) {
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
