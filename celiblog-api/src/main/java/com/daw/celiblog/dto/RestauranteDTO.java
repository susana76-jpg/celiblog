package com.daw.celiblog.dto;

import com.daw.celiblog.enums.EstadoValidacion;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.util.Date;
import java.util.Objects;


public class RestauranteDTO {

    private Long idRestaurante;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fechaPublicacion;
    private UsuarioDTO usuarioDTO;
    private String descripcion;
    private String direccion;
    private String imagenUrl;
    private String nombre;
    private String ubicacion;
    private String urlWeb;
    //@NotBlank(message = "El teléfono no puede estar vacío")
    @Pattern(
            regexp = "^(?:\\+34|0034)?d{9}$",
            message = "El teléfono debe ser válido en España"
    )
    private String telefono;
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Formato de email inválido"
    )
    private String email;
    private int valoracion;
    @Enumerated(EnumType.STRING)
    private EstadoValidacion estado = EstadoValidacion.APROBADO;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fechaValidacion;

    public RestauranteDTO() {
    }

    public RestauranteDTO(Long idRestaurante, Date fechaPublicacion, UsuarioDTO usuarioDTO, String descripcion, String direccion, String imagenUrl, String nombre, String ubicacion, String urlWeb, String telefono, String email, int valoracion, EstadoValidacion estado, Date fechaValidacion) {
        this.idRestaurante = idRestaurante;
        this.fechaPublicacion = fechaPublicacion;
        this.usuarioDTO = usuarioDTO;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.imagenUrl = imagenUrl;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.urlWeb = urlWeb;
        this.telefono = telefono;
        this.email = email;
        this.valoracion = valoracion;
        this.estado = estado;
        this.fechaValidacion = fechaValidacion;
    }

    public Long getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(Long idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fecha) {
        this.fechaPublicacion = fecha;
    }

    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }

    public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
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

    public EstadoValidacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoValidacion estado) {
        this.estado = estado;
    }

    public Date getFechaValidacion() {
        return fechaValidacion;
    }

    public void setFechaValidacion(Date fechaValidacion) {
        this.fechaValidacion = fechaValidacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestauranteDTO that = (RestauranteDTO) o;
        return Objects.equals(idRestaurante, that.idRestaurante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRestaurante);
    }
}
