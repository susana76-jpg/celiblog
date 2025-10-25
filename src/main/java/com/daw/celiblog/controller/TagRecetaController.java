package com.daw.celiblog.controller;

import com.daw.celiblog.dto.RecetaDTO;
import com.daw.celiblog.dto.TagRecetaDTO;
import com.daw.celiblog.dto.TagRecetaView;
import com.daw.celiblog.service.TagRecetaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tag-receta")
public class TagRecetaController {
    @Autowired
    TagRecetaService tagRecetaService;

    @Operation(summary = "Obtiene un listado de los nombres de todos los tags de recetas existentes.")
    @GetMapping("/all")
    public ResponseEntity<List<String>> obtenerPorId() {
        return ResponseEntity.ok(tagRecetaService.obtenerTodosNombresTags());
    }

    @Operation(summary = "Añade tag a una receta..")
    @PostMapping("/add")
    public ResponseEntity<TagRecetaDTO> crearTagReceta(@RequestBody TagRecetaView tagRecetaView){
        TagRecetaDTO nuevoTagReceta = this.tagRecetaService.crearTagReceta(tagRecetaView);
        return ResponseEntity.status(201).body(nuevoTagReceta);
    }



}
