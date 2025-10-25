package com.daw.celiblog.dto;

import com.daw.celiblog.db.entity.Rol;
import com.daw.celiblog.service.mapper.RolMapper;

public class UsuarioDTO {

    private Long idUsuario;
    private String email;
    private String nombre;
    private String password;
    private RolDTO rol;
    private Long idRol;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long idUsuario, String email, String nombre, String password, Rol rol) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.nombre = nombre;
        this.password = password;
        this.rol = RolMapper.entityToDto(rol);
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
}
