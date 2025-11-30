package com.daw.celiblog.controller;

import com.daw.celiblog.dto.RestauranteDTO;
import com.daw.celiblog.dto.TagRestauranteDTO;
import com.daw.celiblog.dto.TagRestauranteView;
import com.daw.celiblog.service.RestauranteService;
import com.daw.celiblog.service.TagRestauranteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Tag Restaurantes", description = "Operaciones relacionadas con los tags de restaurantes.")
@RequestMapping("/api/tag-restaurante")
public class TagRestauranteController {
    @Autowired
    TagRestauranteService tagRestauranteService;
    @Autowired
    private RestauranteService restauranteService;

    @Operation(summary = "Obtiene el listado de todos los nombres de tag de restaurantes, sin duplicados y ordenados alfabéticamente.")
    @GetMapping("public/all")
    public ResponseEntity<List<String>> obtenerPorId() {
        return ResponseEntity.ok(tagRestauranteService.obtenerTodosNombresTags());
    }

    @Operation(summary = "Añade tag a un restaurante por el id del restaurante.")
    @PostMapping("/add")
    public ResponseEntity<TagRestauranteDTO> crearTagRestaurante(@RequestBody TagRestauranteView tagRestauranteView){
        TagRestauranteDTO nuevoTagRestaurante = this.tagRestauranteService.crearTagRestaurante(tagRestauranteView);
        return ResponseEntity.status(201).body(nuevoTagRestaurante);
    }

    @Operation(summary = "Obtiene los tags de un restaurante por su id.")
    @GetMapping("public/tags")
    public ResponseEntity<List<TagRestauranteDTO>> obtenerTagsPorIdRestaurante(@RequestParam(name="idRestaurante") Long idRestaurante) {
        return ResponseEntity.ok(restauranteService.obtenerTagsRestaurantePorId(idRestaurante));
    }


    @Operation(summary = "Obtiene la lista de restaurantes por nombre de tag.")
    @GetMapping("public/restaurantesByTag")
    public ResponseEntity<List<RestauranteDTO>> obtenerRecetasPorNombreTag(@RequestParam(name="nombreTag") String nombreTag) {
        return ResponseEntity.ok(this.restauranteService.buscarRestaurantesPorNombreDeTag(nombreTag));
    }

    @Operation(summary = "Obtiene la lista de restaurantes por varios nombre de tag.")
    @GetMapping("public/restaurantesByTags")
    public ResponseEntity<List<RestauranteDTO>> obtenerRestaurantesPorNombresTag(@RequestParam(name="tag") List<String> tags) {
        return ResponseEntity.ok(this.restauranteService.buscarRestaurantesPorNombresDeTag(tags));
    }


}
