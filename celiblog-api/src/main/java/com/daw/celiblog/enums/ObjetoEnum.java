package com.daw.celiblog.enums;

public enum ObjetoEnum {
    RECETA,
    POST,
    RESTAURANTE,
    PENDIENTE,
    ;


    @Override
    public String toString() {
        return name().toUpperCase();
    }
}
