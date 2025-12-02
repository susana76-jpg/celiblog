package com.daw.celiblog.dto;

import com.daw.celiblog.db.entity.Rol;
import com.daw.celiblog.service.mapper.RolMapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;

import java.util.Date;

public class UsuarioDTO {

    private Long idUsuario;
    private String email;
    private String nombre;
    private String password;
    private RolDTO rol;
    private Long idRol;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fechaAlta;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long idUsuario, String email, String nombre, String password, Rol rol, Date fechaAta) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.nombre = nombre;
        this.password = password;
        this.rol = RolMapper.entityToDto(rol);
        this.fechaAlta = fechaAta;
    }

    public UsuarioDTO(String email, String nombre, String password, Long idRol) {
        this.email = email;
        this.nombre = nombre;
        this.password = password;
        this.idRol = idRol;
    }


    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RolDTO getRol() {
        return rol;
    }

    public void setRol(RolDTO rol) {
        this.rol = rol;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAltata) {
        this.fechaAlta = fechaAltata;
    }
}
