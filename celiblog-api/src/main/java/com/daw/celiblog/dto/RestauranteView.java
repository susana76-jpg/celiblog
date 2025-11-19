package com.daw.celiblog.dto;

import jakarta.validation.constraints.Pattern;

public class RestauranteView {

    public String nombre;
    public String direccion;
    public String descripcion;
    public String urlWeb;
    public String imagen_url;
    public String ubicacion;
    @Pattern(
            regexp = "^(?:\\+34|0034)?d{9}$",
            message = "El teléfono debe ser válido en España"
    )
    public String telefono;
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Formato de email inválido"
    )
    public String email;
    public Long idUsuario;
    public int valoracion;

    public RestauranteView() {
    }

    public RestauranteView(String nombre, String direccion, String descripcion, String urlWeb, String imagen_url, String ubicacion, String telefono, String email, Long idUsuario, int valoracion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.descripcion = descripcion;
        this.urlWeb = urlWeb;
        this.imagen_url = imagen_url;
        this.ubicacion = ubicacion;
        this.telefono = telefono;
        this.email = email;
        this.idUsuario = idUsuario;
        this.valoracion = valoracion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlWeb() {
        return urlWeb;
    }

    public void setUrlWeb(String urlWeb) {
        this.urlWeb = urlWeb;
    }

    public String getImagen_url() {
        return imagen_url;
    }

    public void setImagen_url(String imagen_url) {
        this.imagen_url = imagen_url;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
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

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }
}
