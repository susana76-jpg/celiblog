package com.daw.celiblog.enums;

public enum RolEnum {
    ADMINISTRADOR,
    EDITOR,
    VISITOR;


    @Override
    public String toString() {
        return name().toUpperCase();
    }
}
