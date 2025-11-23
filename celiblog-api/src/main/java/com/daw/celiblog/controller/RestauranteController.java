package com.daw.celiblog.controller;

import com.daw.celiblog.dto.*;
import com.daw.celiblog.enums.EstadoValidacion;
import com.daw.celiblog.service.RestauranteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Restaurantes", description = "Operaciones relacionadas con restaurantes.")
@RequestMapping("/api/restaurante")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @Operation(summary = "Obtiene todos los restaurantes.")
    @GetMapping("/all")
    public ResponseEntity<List<RestauranteDTO>> obtenerTodosRestaurantes() {
        return ResponseEntity.ok(restauranteService.obtenerTodos());
    }

    @Operation(summary = "Obtiene un restaurante por su id.")
    @GetMapping("/byId")
    public ResponseEntity<RestauranteDTO> getRestauranteById(@RequestParam(name="id") Long id) {
        return ResponseEntity.ok(restauranteService.obtenerPorId(id));
    }

    @Operation(summary = "Añade un restaurante.")
    @PostMapping("/add")
    public ResponseEntity<RestauranteDTO> crearRestaurante(@RequestBody RestauranteView restauranteView) throws JsonProcessingException {
        RestauranteDTO nuevoRestaurante = this.restauranteService.crearRestaurante(restauranteView);
        return ResponseEntity.status(201).body(nuevoRestaurante);
    }

    @Operation(summary = "Elimina una restaurante por su id. Elimina también los tags vinculados al restaurante eliminado")
    @DeleteMapping("/deleteById")
    public ResponseEntity<String> deleteRestaurante(@RequestParam(name="id") Long id){
        if(this.restauranteService.eliminar(id)){
            return ResponseEntity.status(200).body("Se ha eliminado el restaurante.");
        }else{
            return ResponseEntity.status(300).body("No existe el restaurante a eliminar.");
        }
    }

    @Operation(summary = "Actualiza los datos de un restaurante.")
    @PutMapping("/update")
    public ResponseEntity<RestauranteDTO> updatePasoReceta(@RequestBody RestauranteView restauranteView, @RequestParam(name="idRestaurante") Long idRestaurante) throws JsonProcessingException {
        return ResponseEntity.ok(this.restauranteService.update(restauranteView, idRestaurante));
    }

    @Operation(summary = "Actualiza los datos de geolocalización de un restaurante, por su dirección completa.")
    @PutMapping("/update-geolocalizacion")
    public ResponseEntity<RestauranteDTO> updatePasoRecetaGeolocalizacion(@RequestBody RestauranteViewSummary restauranteViewSummary, @RequestParam(name="idRestaurante") Long idRestaurante) throws JsonProcessingException {
        return ResponseEntity.ok(this.restauranteService.actualizarGeolocalización(idRestaurante, restauranteViewSummary));
    }

    //GESTIÓN ESTADO DE PUBLICACIÓN
    @Operation(summary = "GESTIÓN ESTADO DE PUBLICACIÓN: Obtiene todos los restaurantes publicados por los usuarios y pendientes de validar para su publicación por un administrador.")
    @GetMapping("/estado-pendiente")
    public ResponseEntity<List<RestauranteDTO>> getRestaurantesEstadoPendiente() {
        return ResponseEntity.ok(this.restauranteService.getRestaurantesEstadoPendiente());
    }

    @Operation(summary = "GESTIÓN ESTADO DE PUBLICACIÓN: Obtiene todos los restaurantes publicados por los usuarios y aprobados para su publicación por un administrador.")
    @GetMapping("/estado-aprobada")
    public ResponseEntity<List<RestauranteDTO>> getRestaurantesEstadoAprobado() {
        return ResponseEntity.ok(this.restauranteService.getRestaurantesEstadoAprobado());
    }

    @Operation(summary = "GESTIÓN ESTADO DE PUBLICACIÓN: Obtiene todos los restaurantes publicados por los usuarios  y rechazados para su publicación por un administrador.")
    @GetMapping("/estado-rechazado")
    public ResponseEntity<List<RestauranteDTO>> getRestaurantesEstadoRechazado() {
        return ResponseEntity.ok(this.restauranteService.getRestaurantesEstadoRechazado());
    }


    @Operation(summary = "GESTIÓN ESTADO DE PUBLICACIÓN: Actualiza el estado de publicación de un restaurante (PENDIENTE, APROBADO, RECHAZADO")
    @PutMapping("/update-estado-publicacion")
    ResponseEntity<RestauranteDTO> updateEstadoPublicacionReceta(@RequestParam(name="idRestaurante") Long idRestaurante, @RequestParam(name="estado") EstadoValidacion estado) {
        return ResponseEntity.ok(this.restauranteService.updateEstadoPublicacionRestaurante(idRestaurante, estado));
    }

}
