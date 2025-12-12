package com.daw.celiblog.dto;

public class AuthResponse {
    private String accessToken;
    private String refreshToken;
    private UsuarioDTO usuarioLogin;

    // Constructor
    public AuthResponse(String accessToken, String refreshToken, UsuarioDTO usuarioLogin) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.usuarioLogin = usuarioLogin;
    }

    // Getters y setters
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public UsuarioDTO getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(UsuarioDTO usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }
}

