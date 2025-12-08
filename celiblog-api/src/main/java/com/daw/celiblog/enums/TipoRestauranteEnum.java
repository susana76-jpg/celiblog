package com.daw.celiblog.enums;

public enum TipoRestauranteEnum {
    SIN_GLUTEN,
    MEDITERRANEA,
    ASIATICA,
    VEGANO,
    MEXICANA;


    @Override
    public String toString() {
        return name().toUpperCase();
    }
}
