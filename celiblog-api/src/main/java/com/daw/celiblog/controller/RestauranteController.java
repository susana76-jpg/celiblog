package com.daw.celiblog.controller;

import com.daw.celiblog.dto.*;
import com.daw.celiblog.enums.EstadoValidacionEnum;
import com.daw.celiblog.service.RestauranteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Restaurantes", description = "Operaciones relacionadas con restaurantes.")
@RequestMapping("/api/restaurante")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @Operation(summary = "PÚBLICO: Obtiene todos los restaurantes.")
    @GetMapping("public/all")
    public ResponseEntity<List<RestauranteDTO>> all(Authentication authentication) {
        return ResponseEntity.ok(restauranteService.all(authentication));
    }
    @Operation(summary = "PÚBLICO: Obtiene un restaurante por su id.")
    @GetMapping("public/byId")
    public ResponseEntity<?> getById(Authentication authentication, @RequestParam(name="id") Long id) {
        if(restauranteService.getById(authentication, id) != null){
            return ResponseEntity.ok(restauranteService.getById(authentication, id));
        }else{
            return ResponseEntity.badRequest().body("No existe objeto.");
        }
    }
    @Operation(summary = "PÚBLICO: Obtiene restaurantes por su ubicación.")
    @GetMapping("public/ubicacion")
    public ResponseEntity<List<RestauranteDTO>> getRestauranteByUbicacion(Authentication authentication, @RequestParam(name="ubicacion") String ubicacion) {
        return ResponseEntity.ok(restauranteService.byUbicacion(authentication, ubicacion));
    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','EDITOR','VISITOR')")
    @Operation(summary = "PROTEGIDO: Añade un restaurante.")
    @PostMapping("/add")
    public ResponseEntity<?> add(Authentication authentication, @RequestBody RestauranteView restauranteView) throws JsonProcessingException {
        RestauranteDTO nuevoRestaurante = this.restauranteService.add(authentication, restauranteView);
        if(nuevoRestaurante != null){
            return ResponseEntity.ok(nuevoRestaurante);
        }else{
            return ResponseEntity.badRequest().body("No se añadió el objeto.");
        }
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @Operation(summary = "ADMINISTRADOR: Elimina una restaurante por su id. Elimina también los tags vinculados al restaurante eliminado")
    @DeleteMapping("/deleteById")
    public ResponseEntity<String> delete(@RequestParam(name="id") Long id){
        if(this.restauranteService.deleteById(id)){
            return ResponseEntity.status(200).body("Se ha eliminado el restaurante.");
        }else{
            return ResponseEntity.status(300).body("No existe el restaurante a eliminar.");
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','EDITOR','VISITOR')")
    @Operation(summary = "PROTEGIDO: Actualiza los datos de un restaurante.")
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody RestauranteView restauranteView, @RequestParam(name="idRestaurante") Long idRestaurante) throws JsonProcessingException {
        RestauranteDTO restaurante = this.restauranteService.update(restauranteView, idRestaurante);
        if(restaurante != null){
            return ResponseEntity.ok(restaurante);
        }else{
            return ResponseEntity.badRequest().body("No se actualizó el objeto.");
        }
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @Operation(summary = "ADMINISTRADOR: Actualiza todos los datos de geolocalización de todos restaurantes, por su dirección completa.")
    @PutMapping("/update-geolocalizacion")
    public ResponseEntity<String> updateAllRestaurantesGeolocalizacion() throws JsonProcessingException {
        this.restauranteService.actualizarGeolocalizacion();
        return ResponseEntity.ok("Restaurantes actualizados en su longitud y latitud desde su dirección");
    }

    //GESTIÓN ESTADO DE PUBLICACIÓN
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @Operation(summary = "ADMINISTRADOR - GESTIÓN ESTADO DE PUBLICACIÓN: Obtiene todos los restaurantes publicados por los usuarios y pendientes de validar para su publicación por un administrador.")
    @GetMapping("/estado-pendiente")
    public ResponseEntity<List<RestauranteDTO>> getRestaurantesEstadoPendiente() {
        return ResponseEntity.ok(this.restauranteService.getRestaurantesEstadoPendiente());
    }

    @Operation(summary = "Obtiene todos los restaurantes publicados por los usuarios y aprobados para su publicación por un administrador.")
    @GetMapping("public/estado-aprobada")
    public ResponseEntity<List<RestauranteDTO>> getRestaurantesEstadoAprobado() {
        return ResponseEntity.ok(this.restauranteService.getRestaurantesEstadoAprobado());
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @Operation(summary = "ADMINISTRADOR - GESTIÓN ESTADO DE PUBLICACIÓN: Obtiene todos los restaurantes publicados por los usuarios  y rechazados para su publicación por un administrador.")
    @GetMapping("/estado-rechazado")
    public ResponseEntity<List<RestauranteDTO>> getRestaurantesEstadoRechazado() {
        return ResponseEntity.ok(this.restauranteService.getRestaurantesEstadoRechazado());
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @Operation(summary = "ADMINISTRADOR - GESTIÓN ESTADO DE PUBLICACIÓN: Actualiza el estado de publicación de un restaurante (PENDIENTE, APROBADO, RECHAZADO")
    @PutMapping("/update-estado-publicacion")
    ResponseEntity<RestauranteDTO> updateEstadoPublicacionRestaurante(@RequestParam(name="idRestaurante") Long idRestaurante, @RequestParam(name="estado") EstadoValidacionEnum estado) {
        return ResponseEntity.ok(this.restauranteService.updateEstadoPublicacionRestaurante(idRestaurante, estado));
    }


}
