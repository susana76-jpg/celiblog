package com.daw.celiblog.controller;

import com.daw.celiblog.dto.EstadisticaDTO;
import com.daw.celiblog.enums.EstadoValidacionEnum;
import com.daw.celiblog.enums.ObjetoEnum;
import com.daw.celiblog.service.GestionPublicacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Estadísticas", description = "Operaciones relacionadas los totales de objetos en base de datos")
@RequestMapping("/api/estadistica")
public class EstadisticaController {
    @Autowired
    private GestionPublicacionService gestionPublicacionService;



    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @Operation(summary = "PRIVADO : Devuelve las estadísticas de todos los objetos (número total de objetos en base de datos).")
    @GetMapping("/all")
    public ResponseEntity<EstadisticaDTO> getEstadistica(Authentication authentication) {
        return ResponseEntity.ok(this.gestionPublicacionService.getEstadisticasObjetos());
    }


}
