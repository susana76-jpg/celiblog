package com.daw.celiblog.dto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ErrorDTO {
    private String mensaje;
    private Object recursoId;
    private LocalDateTime timestamp;

    public ErrorDTO(Exception ex, Object id, String mensaje){
        this.mensaje = mensaje;
        this.recursoId = id;
        this.timestamp = LocalDateTime.now();

        Map<String, Object> cuerpo = new HashMap<>();
        cuerpo.put("mensaje", ex.getMessage());
        cuerpo.put("id", this.recursoId);
        cuerpo.put("timestamp", LocalDateTime.now());

    }

    /*
    como utilizarlo:
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
    .body(new ErrorDTO(ex.getMessage(), ex.getRecursoId(), LocalDateTime.now()));

     */

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Object getRecursoId() {
        return recursoId;
    }

    public void setRecursoId(Object recursoId) {
        this.recursoId = recursoId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
