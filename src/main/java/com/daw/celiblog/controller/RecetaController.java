package com.daw.celiblog.controller;

import com.daw.celiblog.dto.*;
import com.daw.celiblog.service.RecetaService;
import com.daw.celiblog.service.TagRecetaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/receta")
public class RecetaController {

    @PostConstruct
    public void init() {
        System.out.println("DB_USER_NAME: " + System.getenv("DB_USER_NAME"));
        System.out.println("DB_PASSWORD: " + System.getenv("DB_PASSWORD"));
    }

    @Autowired
    RecetaService recetaService;

    @Autowired
    TagRecetaService tagRecetaService;

    @Operation(summary = "Todas las recetas registradas.")
    @GetMapping("/all")
    public ResponseEntity<List<RecetaDTO>> obtenerTodos() {
        return ResponseEntity.ok(recetaService.obtenerTodos());
    }

    @Operation(summary = "Receta por su id.")
    @GetMapping("/byId")
    public ResponseEntity<RecetaDTO> getRecetaById(@RequestParam(name="id") Long id) {
        return ResponseEntity.ok(recetaService.obtenerPorId(id));
    }

    @Operation(summary = "Lista de tags de una receta por su id.")
    @GetMapping("/tags")
    public ResponseEntity<List<TagRecetaDTO>> obtenerTagsPorIdReceta(@RequestParam(name="idReceta") Long idReceta) {
        return ResponseEntity.ok(recetaService.obtenerTagsRecetaPorId(idReceta));
    }

      @Operation(summary = "Pasos para hacer la receta, por id de receta y en orden de paso.")
    @GetMapping("/pasos")
    public ResponseEntity<List<PasoRecetaDTO>> obtenerPasosRecetaPorIdReceta(@RequestParam(name="idReceta") Long idReceta) {
        return ResponseEntity.ok(recetaService.obtenerPasosRecetaPorId(idReceta));
    }

    @Operation(summary = "Añade una receta.")
    @PostMapping("/add")
    public ResponseEntity<RecetaDTO> crearReceta(@RequestBody RecetaDTO recetaDTO){
        RecetaDTO nuevaReceta = this.recetaService.crearReceta(recetaDTO);
        return ResponseEntity.status(201).body(nuevaReceta);
    }

    @Operation(summary = "Lista de recetas por nombre de tag.")
    @GetMapping("/recetasByTag")
    public ResponseEntity<List<RecetaDTO>> obtenerRecetasPorNombreTag(@RequestParam(name="nombreTag") String nombreTag) {
        return ResponseEntity.ok(this.recetaService.buscarRecetasPorNombreDeTag(nombreTag));
    }

    @Operation(summary = "Lista de recetas por varios nombre de tag.")
    @GetMapping("/recetasByTags")
    public ResponseEntity<List<RecetaDTO>> buscarRecetasPorNombreDeTags(@RequestParam(name="tag") List<String> tags) {
        return ResponseEntity.ok(this.recetaService.buscarRecetasPorNombreDeTags(tags));
    }









}
