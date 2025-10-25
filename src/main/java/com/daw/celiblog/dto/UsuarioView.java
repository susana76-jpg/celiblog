package com.daw.celiblog.dto;

public class UsuarioView {
    private String email;
    private String nombre;
    private String password;
    private Long idRol;

    public UsuarioView(String email, String nombre, String password, Long idRol) {
        this.email = email;
        this.nombre = nombre;
        this.password = password;
        this.idRol = idRol;
    }

    public UsuarioView() {
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

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }
}
