package com.daw.celiblog.enums;

public enum TipoComidaEnum {
    DESAYUNO,
    BRUNCH,
    ALMUERZO,
    MERIENDA,
    CENA,
    TAPAS,
    RACIONES,
    POSTRE,
    SALADO,
    DULCE;

    @Override
    public String toString() {
        return name().toUpperCase();
    }
}
