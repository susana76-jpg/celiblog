package com.daw.celiblog.controller;

import com.daw.celiblog.dto.EstadisticaDTO;
import com.daw.celiblog.enums.ObjetoEnum;
import com.daw.celiblog.service.FavoritoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Favoritos", description = "Operaciones relacionadas con los favoritos del usuario.")
@RequestMapping("/api/favoritos")
public class FavoritoController {

    @Autowired
    private FavoritoService favoritoService;

    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','EDITOR','VISITOR')")
    @Operation(summary = "PROTEGIDO: Obtiene el listado de los favoritos del usuario de: restaurante, recetas, post o comentarios.")
    @GetMapping("/byReferencia")
    public ResponseEntity<List<?>> obtenerFavoritos(
        Authentication authentication,
        @RequestParam(name="tipoReferencia") ObjetoEnum tipoReferencia) {
        return ResponseEntity.ok(this.favoritoService.getFavoritosByReferencia(authentication, tipoReferencia));
    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','EDITOR','VISITOR')")
    @Operation(summary = "PROTEGIDO: Obtiene las estadísticas de favoritos del usuario logado.")
    @GetMapping("/estadistica")
    public ResponseEntity<EstadisticaDTO> obtenerEstadisticaFavoritos(
            Authentication authentication) {
        return ResponseEntity.ok(this.favoritoService.getFavoritosEstadistica(authentication));
    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','EDITOR','VISITOR')")
    @Operation(summary = "PROTEGIDO: Añade un favorito.")
    @GetMapping("/add")
    public ResponseEntity<?> creaFavorito(Authentication authentication,
        @RequestParam(name="idReferencia")Long idReferencia,
        @RequestParam(name="tipoReferencia") ObjetoEnum tipoReferencia){

        if(this.favoritoService.addFavorito(authentication, idReferencia, tipoReferencia) != null){
            return ResponseEntity.ok(this.favoritoService.addFavorito(authentication, idReferencia, tipoReferencia));
        }else{
            return ResponseEntity.badRequest().body("No existe el objeto al que quieres hacer favorito.");
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','EDITOR','VISITOR')")
    @Operation(summary = "PROTEGIDO: Elimina un favorito por su id.")
    @DeleteMapping("/deleteById")
    public ResponseEntity<String> deleteFavorito(Authentication authentication, @RequestParam(name="idFavorito") Long idFavorito){
        if(this.favoritoService.deleteFavorito(idFavorito)){
            return ResponseEntity.status(200).body("Se ha eliminado el favorito con id "+idFavorito);
        }else{
            return ResponseEntity.status(300).body("No existe el favorito a eliminar.");
        }

    }












}
