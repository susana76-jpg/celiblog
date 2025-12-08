package com.daw.celiblog.dto;

import com.daw.celiblog.enums.EstadoValidacionEnum;
import com.daw.celiblog.enums.TipoRestauranteEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.util.Date;
import java.util.Objects;


public class RestauranteDTO {

    private Long idRestaurante;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fechaPublicacion;
    @JsonProperty("usuarioPublicacion")
    private UsuarioSummaryDTO usuario;
    @ManyToOne
    @JsonIgnore
    private UsuarioDTO usuarioDTO;
    private String titulo;
    private String subtitulo;
    private String descripcion;
    private String direccion;
    private String imagenUrl;
    private String nombre;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_restaurante", nullable = false)
    private TipoRestauranteEnum tipoRestaurante = TipoRestauranteEnum.SIN_GLUTEN;
    private String ubicacion;
    private int codigoPostal;
    private Double latitud;
    private Double longitud;
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
    private EstadoValidacionEnum estado = EstadoValidacionEnum.APROBADO;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fechaValidacion;
    private boolean esFavoritoUsuario;

    public RestauranteDTO() {
    }

    public RestauranteDTO(Long idRestaurante, Date fechaPublicacion, UsuarioSummaryDTO usuario, String descripcion, TipoRestauranteEnum tipoRestaurante, String titulo,String subtitulo, String direccion, String imagenUrl, String nombre, String ubicacion, int codigoPostal, Double latitud, Double longitud, String urlWeb, String telefono, String email, int valoracion, EstadoValidacionEnum estado, Date fechaValidacion) {
        this.idRestaurante = idRestaurante;
        this.fechaPublicacion = fechaPublicacion;
        this.usuario = usuario;
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.descripcion = descripcion;
        this.tipoRestaurante = tipoRestaurante;
        this.direccion = direccion;
        this.imagenUrl = imagenUrl;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.codigoPostal = codigoPostal;
        this.latitud = latitud;
        this.longitud = longitud;
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

    public UsuarioSummaryDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioSummaryDTO usuario) {
        this.usuario = usuario;
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

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
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

    public EstadoValidacionEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoValidacionEnum estado) {
        this.estado = estado;
    }

    public Date getFechaValidacion() {
        return fechaValidacion;
    }

    public void setFechaValidacion(Date fechaValidacion) {
        this.fechaValidacion = fechaValidacion;
    }

    public boolean isEsFavoritoUsuario() {
        return esFavoritoUsuario;
    }

    public void setEsFavoritoUsuario(boolean esFavoritoUsuario) {
        this.esFavoritoUsuario = esFavoritoUsuario;
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

    public TipoRestauranteEnum getTipoRestaurante() {
        return tipoRestaurante;
    }

    public void setTipoRestaurante(TipoRestauranteEnum tipoRestaurante) {
        this.tipoRestaurante = tipoRestaurante;
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
