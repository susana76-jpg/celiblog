package com.daw.celiblog.controller;

import com.daw.celiblog.dto.ComentarioRestauranteDTO;
import com.daw.celiblog.dto.RestauranteDTO;
import com.daw.celiblog.dto.TagRestauranteDTO;
import com.daw.celiblog.service.RestauranteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/restaurante")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping("/all")
    public ResponseEntity<List<RestauranteDTO>> obtenerPorId() {
        return ResponseEntity.ok(restauranteService.obtenerTodos());
    }

    @GetMapping("/id")
    public ResponseEntity<RestauranteDTO> getRestauranteById(@RequestParam(name="id") Long id) {
        return ResponseEntity.ok(restauranteService.obtenerPorId(id));
    }

    @GetMapping("/tags")
    public ResponseEntity<List<TagRestauranteDTO>> obtenerTagsPorIdRestaurante(@RequestParam(name="idRestaurante") Long idRestaurante) {
        return ResponseEntity.ok(restauranteService.obtenerTagsRestaurantePorId(idRestaurante));
    }

    @Operation(summary = "Comentarios realizados al restaurante ordenados por fecha.")
    @GetMapping("/comentarios")
    public ResponseEntity<List<ComentarioRestauranteDTO>> obtenerComentariosPorIdRestaurante(@RequestParam(name="idRestaurante") Long idRestaurante) {
        return ResponseEntity.ok(restauranteService.obtenerComentariosByIdRestaurante(idRestaurante));
    }





}
