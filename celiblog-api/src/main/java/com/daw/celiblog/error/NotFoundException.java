package com.daw.celiblog.error;

public class NotFoundException extends RuntimeException {

    private final Object recursoId;

    public NotFoundException(String mensaje, Object recursoId) {
        super(mensaje);
        this.recursoId = recursoId;
    }

    public Object getRecursoId() {
        return recursoId;
    }
}
