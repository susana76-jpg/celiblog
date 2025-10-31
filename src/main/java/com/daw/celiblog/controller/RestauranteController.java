package com.daw.celiblog.controller;

import com.daw.celiblog.dto.RestauranteDTO;
import com.daw.celiblog.dto.TagRestauranteDTO;
import com.daw.celiblog.service.RestauranteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @Operation(summary = "Obtiene los tags de un restaurante por su id.")
    @GetMapping("/tags")
    public ResponseEntity<List<TagRestauranteDTO>> obtenerTagsPorIdRestaurante(@RequestParam(name="idRestaurante") Long idRestaurante) {
        return ResponseEntity.ok(restauranteService.obtenerTagsRestaurantePorId(idRestaurante));
    }


    @Operation(summary = "Obtiene la lista de restaurantes por nombre de tag.")
    @GetMapping("/restaurantesByTag")
    public ResponseEntity<List<RestauranteDTO>> obtenerRecetasPorNombreTag(@RequestParam(name="nombreTag") String nombreTag) {
        return ResponseEntity.ok(this.restauranteService.buscarRestaurantesPorNombreDeTag(nombreTag));
    }

    @Operation(summary = "Obtiene la lista de restaurantes por varios nombre de tag.")
    @GetMapping("/restaurantesByTags")
    public ResponseEntity<List<RestauranteDTO>> obtenerRestaurantesPorNombresTag(@RequestParam(name="tag") List<String> tags) {
        return ResponseEntity.ok(this.restauranteService.buscarRestaurantesPorNombresDeTag(tags));
    }





}
