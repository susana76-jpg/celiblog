package com.daw.celiblog.dto;

public class UsuarioSummaryDTO {
    private String email;
    private String nombre;

    public UsuarioSummaryDTO() {
    }

    public UsuarioSummaryDTO(String email, String nombre) {
        this.email = email;
        this.nombre = nombre;
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
}
