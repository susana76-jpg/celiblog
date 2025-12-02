package com.daw.celiblog.enums;

public enum ObjetoEnum {
    RECETA,
    POST,
    RESTAURANTE,
    COMENTARIO,
    PENDIENTE,
    ;


    @Override
    public String toString() {
        return name().toUpperCase();
    }
}
