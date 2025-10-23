package com.daw.celiblog.controller;

import com.daw.celiblog.dto.TagRestauranteDTO;
import com.daw.celiblog.dto.TagRestauranteView;
import com.daw.celiblog.service.TagRestauranteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tagRestaurante")
public class TagRestauranteController {
    @Autowired
    TagRestauranteService tagRestauranteService;

    @Operation(summary = "Todos los tags de restaurantes sin duplicados.")
    @GetMapping("/all")
    public ResponseEntity<List<String>> obtenerPorId() {
        return ResponseEntity.ok(tagRestauranteService.obtenerTodosNombresTags());
    }

    @Operation(summary = "Añade tag a un restaurante por su nombre y el id del restaurante.")
    @PostMapping("/add")
    public ResponseEntity<TagRestauranteDTO> crearTagRestaurante(@RequestBody TagRestauranteView tagRestauranteView){
        TagRestauranteDTO nuevoTagRestaurante = this.tagRestauranteService.crearTagRestaurante(tagRestauranteView);
        return ResponseEntity.status(201).body(nuevoTagRestaurante);
    }


}
