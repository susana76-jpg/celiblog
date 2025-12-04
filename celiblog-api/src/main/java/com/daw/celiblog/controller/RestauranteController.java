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

    @Operation(summary = "Obtiene todos los restaurantes.")
    @GetMapping("public/all")
    public ResponseEntity<List<RestauranteDTO>> obtenerTodosRestaurantes() {
        return ResponseEntity.ok(restauranteService.obtenerTodos());
    }

    @Operation(summary = "Obtiene un restaurante por su id.")
    @GetMapping("public/byId")
    public ResponseEntity<RestauranteDTO> getRestauranteById(@RequestParam(name="id") Long id) {
        return ResponseEntity.ok(restauranteService.obtenerPorId(id));
    }

    @Operation(summary = "Obtiene restaurantes por su ubicación.")
    @GetMapping("public/ubicacion")
    public ResponseEntity<List<RestauranteDTO>> getRestauranteByUbicacion(@RequestParam(name="ubicacion") String ubicacion) {
        return ResponseEntity.ok(restauranteService.byUbicacion(ubicacion));
    }
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','EDITOR','VISITOR')")
    @Operation(summary = "Añade un restaurante.")
    @PostMapping("visitor/add")
    public ResponseEntity<RestauranteDTO> crearRestaurante(@RequestBody RestauranteView restauranteView) throws JsonProcessingException {
        RestauranteDTO nuevoRestaurante = this.restauranteService.crearRestaurante(restauranteView);
        return ResponseEntity.status(201).body(nuevoRestaurante);
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @Operation(summary = "Elimina una restaurante por su id. Elimina también los tags vinculados al restaurante eliminado")
    @DeleteMapping("admin/deleteById")
    public ResponseEntity<String> deleteRestaurante(@RequestParam(name="id") Long id){
        if(this.restauranteService.eliminar(id)){
            return ResponseEntity.status(200).body("Se ha eliminado el restaurante.");
        }else{
            return ResponseEntity.status(300).body("No existe el restaurante a eliminar.");
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','EDITOR','VISITOR')")
    @Operation(summary = "Actualiza los datos de un restaurante.")
    @PutMapping("visitor/update")
    public ResponseEntity<RestauranteDTO> updatePasoReceta(@RequestBody RestauranteView restauranteView, @RequestParam(name="idRestaurante") Long idRestaurante) throws JsonProcessingException {
        return ResponseEntity.ok(this.restauranteService.update(restauranteView, idRestaurante));
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @Operation(summary = "Actualiza todos los datos de geolocalización de todos restaurantes, por su dirección completa.")
    @PutMapping("admin/update-geolocalizacion")
    public ResponseEntity<String> updateAllRestaurantesGeolocalizacion() throws JsonProcessingException {
        this.restauranteService.actualizarGeolocalizacion();
        return ResponseEntity.ok("Restaurantes actualizados en su longitud y latitud desde su dirección");
    }

    //GESTIÓN ESTADO DE PUBLICACIÓN
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @Operation(summary = "GESTIÓN ESTADO DE PUBLICACIÓN: Obtiene todos los restaurantes publicados por los usuarios y pendientes de validar para su publicación por un administrador.")
    @GetMapping("admin/estado-pendiente")
    public ResponseEntity<List<RestauranteDTO>> getRestaurantesEstadoPendiente() {
        return ResponseEntity.ok(this.restauranteService.getRestaurantesEstadoPendiente());
    }

    @Operation(summary = "GESTIÓN ESTADO DE PUBLICACIÓN: Obtiene todos los restaurantes publicados por los usuarios y aprobados para su publicación por un administrador.")
    @GetMapping("public/estado-aprobada")
    public ResponseEntity<List<RestauranteDTO>> getRestaurantesEstadoAprobado() {
        return ResponseEntity.ok(this.restauranteService.getRestaurantesEstadoAprobado());
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @Operation(summary = "GESTIÓN ESTADO DE PUBLICACIÓN: Obtiene todos los restaurantes publicados por los usuarios  y rechazados para su publicación por un administrador.")
    @GetMapping("admin/estado-rechazado")
    public ResponseEntity<List<RestauranteDTO>> getRestaurantesEstadoRechazado() {
        return ResponseEntity.ok(this.restauranteService.getRestaurantesEstadoRechazado());
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @Operation(summary = "GESTIÓN ESTADO DE PUBLICACIÓN: Actualiza el estado de publicación de un restaurante (PENDIENTE, APROBADO, RECHAZADO")
    @PutMapping("admin/update-estado-publicacion")
    ResponseEntity<RestauranteDTO> updateEstadoPublicacionReceta(@RequestParam(name="idRestaurante") Long idRestaurante, @RequestParam(name="estado") EstadoValidacionEnum estado) {
        return ResponseEntity.ok(this.restauranteService.updateEstadoPublicacionRestaurante(idRestaurante, estado));
    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','EDITOR','VISITOR')")
    @Operation(summary = "USUARIO LOGADO: Restaurantes favoritos del usuario logado.")
    @GetMapping("visitor/allFavoritos")
    public ResponseEntity<List<RestauranteDTO>> obtenerRestaurantesFavoritasUsuario(Authentication authentication) {
        return ResponseEntity.ok(restauranteService.obtenerRestaurantesFavoritosUsuario(authentication.getName()));
    }

}
