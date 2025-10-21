package com.daw.celiblog.dto;


import java.util.Date;

public class ComentarioRestauranteDTO {

    private Long idComentarioRestaurante;
    private int valoracion;
    private ComentarioDTO comentario;
    private RestauranteDTO restaurante;
    private Long idRestaurante;
    private Date fecha;

    public ComentarioRestauranteDTO(Long idComentarioRestaurante, int valoracion, ComentarioDTO comentario, RestauranteDTO restaurante, Long idRestaurante, Date fecha) {
        this.idComentarioRestaurante = idComentarioRestaurante;
        this.valoracion = valoracion;
        this.comentario = comentario;
        this.restaurante = restaurante;
        this.idRestaurante = idRestaurante;
        this.fecha = fecha;
    }
    public ComentarioRestauranteDTO(Long idComentarioRestaurante, int valoracion, ComentarioDTO comentario, Long idRestaurante, Date fecha) {
        this.idComentarioRestaurante = idComentarioRestaurante;
        this.valoracion = valoracion;
        this.comentario = comentario;
        this.idRestaurante = idRestaurante;
        this.fecha = fecha;
    }


    public ComentarioRestauranteDTO() {}

    public Long getIdComentarioRestaurante() {
        return idComentarioRestaurante;
    }

    public void setIdComentarioRestaurante(Long idComentarioRestaurante) {
        this.idComentarioRestaurante = idComentarioRestaurante;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public ComentarioDTO getComentario() {
        return comentario;
    }

    public void setComentario(ComentarioDTO comentario) {
        this.comentario = comentario;
    }

    public RestauranteDTO getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(RestauranteDTO restaurante) {
        this.restaurante = restaurante;
    }

    public Long getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(Long idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
