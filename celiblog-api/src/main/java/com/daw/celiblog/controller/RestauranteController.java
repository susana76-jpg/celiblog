package com.daw.celiblog.controller;

import com.daw.celiblog.dto.RestauranteDTO;
import com.daw.celiblog.dto.RestauranteView;
import com.daw.celiblog.enums.TipoComidaEnum;
import com.daw.celiblog.enums.TipoRestauranteEnum;
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
    @Operation(summary = "PÚBLICO: Obtiene restaurantes por su ubicación y por el tipo de restaurante.")
    @GetMapping("public/ubicacion")
    public ResponseEntity<List<RestauranteDTO>> getRestauranteByUbicacion(
            Authentication authentication,
            @RequestParam(required = false, name="ubicacion") String ubicacion,
            @RequestParam(required = false, name="tiposRestaurante") List<TipoRestauranteEnum> tiposRestaurante) {
        return ResponseEntity.ok(restauranteService.byUbicacionAndTipo(authentication, ubicacion, tiposRestaurante));
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



}
