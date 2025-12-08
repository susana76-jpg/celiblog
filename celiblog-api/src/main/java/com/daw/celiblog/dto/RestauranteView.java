package com.daw.celiblog.dto;

import com.daw.celiblog.enums.EstadoValidacionEnum;
import com.daw.celiblog.enums.TipoRestauranteEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Pattern;

import java.util.Date;
import java.util.Objects;


public class RestauranteView {

    private String titulo;
    private String subtitulo;
    private String descripcion;
    private String direccion;
    private String imagenUrl;
    private String nombre;
    private String ubicacion;
    private int codigoPostal;
    private String urlWeb;
    private String telefono;
    private String email;
    private int valoracion;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_restaurante", nullable = false)
    private TipoRestauranteEnum tipoRestaurante = TipoRestauranteEnum.SIN_GLUTEN;


    public RestauranteView() {
    }

    public RestauranteView(String titulo, String subtitulo, String descripcion, TipoRestauranteEnum tipoRestaurante, String direccion, String imagenUrl, String nombre, String ubicacion, int codigoPostal, String urlWeb, String telefono, String email, int valoracion) {
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.descripcion = descripcion;
        this.tipoRestaurante = tipoRestaurante;
        this.direccion = direccion;
        this.imagenUrl = imagenUrl;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.codigoPostal = codigoPostal;
        this.urlWeb = urlWeb;
        this.telefono = telefono;
        this.email = email;
        this.valoracion = valoracion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getUrlWeb() {
        return urlWeb;
    }

    public void setUrlWeb(String urlWeb) {
        this.urlWeb = urlWeb;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public TipoRestauranteEnum getTipoRestaurante() {
        return tipoRestaurante;
    }

    public void setTipoRestaurante(TipoRestauranteEnum tipoRestaurante) {
        this.tipoRestaurante = tipoRestaurante;
    }
}
