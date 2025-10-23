package com.daw.celiblog.controller;

import com.daw.celiblog.dto.TagRecetaDTO;
import com.daw.celiblog.dto.TagRecetaView;
import com.daw.celiblog.service.TagRecetaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tagReceta")
public class TagRecetaController {
    @Autowired
    TagRecetaService tagRecetaService;

    @Operation(summary = "Todos los tags de receta  sin duplicados.")
    @GetMapping("/all")
    public ResponseEntity<List<String>> obtenerPorId() {
        return ResponseEntity.ok(tagRecetaService.obtenerTodosNombresTags());
    }

    @Operation(summary = "Añade tag a una receta por su nombre y el id de la receta.")
    @PostMapping("/add")
    public ResponseEntity<TagRecetaDTO> crearTagReceta(@RequestBody TagRecetaView tagRecetaView){
        TagRecetaDTO nuevoTagReceta = this.tagRecetaService.crearTagReceta(tagRecetaView);
        return ResponseEntity.status(201).body(nuevoTagReceta);
    }


}
