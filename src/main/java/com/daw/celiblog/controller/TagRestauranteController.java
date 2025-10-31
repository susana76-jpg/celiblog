package com.daw.celiblog.controller;

import com.daw.celiblog.dto.TagRestauranteDTO;
import com.daw.celiblog.dto.TagRestauranteView;
import com.daw.celiblog.service.TagRestauranteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Restaurantes", description = "Operaciones relacionadas con los tags de restaurantes.")
@RequestMapping("/api/tag-restaurante")
public class TagRestauranteController {
    @Autowired
    TagRestauranteService tagRestauranteService;

    @Operation(summary = "Obtiene el listado de todos los nombres de tag de restaurantes, sin duplicados y ordenados alfabéticamente.")
    @GetMapping("/all")
    public ResponseEntity<List<String>> obtenerPorId() {
        return ResponseEntity.ok(tagRestauranteService.obtenerTodosNombresTags());
    }

    @Operation(summary = "Añade tag a un restaurante por el id del restaurante.")
    @PostMapping("/add")
    public ResponseEntity<TagRestauranteDTO> crearTagRestaurante(@RequestBody TagRestauranteView tagRestauranteView){
        TagRestauranteDTO nuevoTagRestaurante = this.tagRestauranteService.crearTagRestaurante(tagRestauranteView);
        return ResponseEntity.status(201).body(nuevoTagRestaurante);
    }


}
