package com.daw.celiblog.controller;

import com.daw.celiblog.dto.RecetaDTO;
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
@Tag(name = "Gestión de publicaciones", description = "Operaciones relacionadas con la gestión de las publicaciones realizadas por los usuarios, tanto de RECETAS, RESTAURANTES, POST o COMENTARIOS.")
@RequestMapping("/api/publicacion")
public class GestionPublicacionController {
    @Autowired
    private GestionPublicacionService gestionPublicacionService;

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @Operation(summary = "PRIVADO : Obtiene todas las objetos publicadas por los usuarios, según su estado de publicación, y su tipo de referencia.")
    @GetMapping("/list")
    public ResponseEntity<List<?>> getObjetosByEstado(Authentication authentication,
            @RequestParam(name="objeto") ObjetoEnum objeto,
            @RequestParam(name="estado") EstadoValidacionEnum estado) {
        return ResponseEntity.ok(this.gestionPublicacionService.getObjetosByEstado(authentication,objeto,estado));
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @Operation(summary = "GESTIÓN ESTADO DE PUBLICACIÓN: Actualiza el estado de publicación de un objeto")
    @PutMapping("/update")
    ResponseEntity<?> update(Authentication authentication,
                            @RequestParam(name="idReceta") Long idReceta,
                            @RequestParam(name="objeto") ObjetoEnum objeto,
                            @RequestParam(name="estado") EstadoValidacionEnum estado) {

        Object objetoActualizado = this.gestionPublicacionService.updateObjetoEstadoPublicacion(authentication, idReceta, objeto, estado);
        if(objetoActualizado != null){
            return ResponseEntity.status(200).body("Se ha atualizado el objeto "+ objeto.toString()+" con id "+idReceta);
        }else{
            return ResponseEntity.status(300).body("No existe el objeto a actualizar.");
        }

    }
}
