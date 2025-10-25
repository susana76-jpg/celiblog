package com.daw.celiblog.dto;

import com.daw.celiblog.db.entity.ComentarioRestaurante;
import com.daw.celiblog.db.entity.Rol;
import com.daw.celiblog.db.entity.TagRestaurante;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RestauranteDTO {

    private Long idRestaurante;
    private String descripcion;
    private String direccion;
    private String imagenUrl;
    private String nombre;
    private String ubicacion;
    private String urlWeb;
    private List<ComentarioRestaurante> comentarioRestaurantes;
    private List<TagRestaurante> tagRestaurantes;
    private String email;
    private int telefono;

    public RestauranteDTO(Long idRestaurante, String descripcion, String direccion, String imagenUrl, String nombre, String ubicacion, String urlWeb, int telefono, String email ) {
        this.idRestaurante = idRestaurante;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.imagenUrl = imagenUrl;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.urlWeb = urlWeb;
        this.telefono = telefono;
        this.email = email;
    }

    public Long getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(Long idRestaurante) {
        this.idRestaurante = idRestaurante;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public List<ComentarioRestaurante> getComentarioRestaurantes() {
        return comentarioRestaurantes;
    }

    public void setComentarioRestaurantes(List<ComentarioRestaurante> comentarioRestaurantes) {
        this.comentarioRestaurantes = comentarioRestaurantes;
    }

    public List<TagRestaurante> getTagRestaurantes() {
        return tagRestaurantes;
    }

    public void setTagRestaurantes(List<TagRestaurante> tagRestaurantes) {
        this.tagRestaurantes = tagRestaurantes;
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
