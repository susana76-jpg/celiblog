package com.daw.celiblog.controller;

import com.daw.celiblog.dto.*;
import com.daw.celiblog.service.PasoRecetaService;
import com.daw.celiblog.service.RecetaService;
import com.daw.celiblog.service.TagRecetaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Tag(name = "Recetas", description = "Operaciones relacionadas con recetas.")
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
    PasoRecetaService pasoRecetaService;


    @Operation(summary = "Obtiene todas las recetas registradas.")
    @GetMapping("/all")
    public ResponseEntity<List<RecetaDTO>> obtenerTodos() {
        return ResponseEntity.ok(recetaService.obtenerTodos());
    }

    @Operation(summary = "Obtiene la receta por su id.")
    @GetMapping("/byId")
    public ResponseEntity<RecetaDTO> getRecetaById(@RequestParam(name="id") Long id) {
        return ResponseEntity.ok(this.recetaService.obtenerPorId(id));
    }
    @Operation(summary = "Añade una receta.")
    @PostMapping("/add")
    public ResponseEntity<RecetaDTO> crearReceta(@RequestBody RecetaDTO recetaDTO){
        RecetaDTO nuevaReceta = this.recetaService.crearReceta(recetaDTO);
        return ResponseEntity.status(201).body(nuevaReceta);
    }

    @Operation(summary = "Elimina una receta por su id. Elimina también los ingredientes y pasos de la receta.")
    @DeleteMapping("/deleteById")
    public ResponseEntity<String> deleteReceta(@RequestParam(name="id") Long id){
        if(this.recetaService.deleteReceta(id)){
            return ResponseEntity.status(200).body("Se ha eliminado la receta, sus ingredientes y pasos correctamente.");
        }else{
            return ResponseEntity.status(300).body("No existe la receta a eliminar.");
        }

    }

//TAG_RECETA
    @Operation(summary = "Obtiene la lista de recetas por nombre de un tag.")
    @GetMapping("/recetasByTag")
    public ResponseEntity<List<RecetaDTO>> obtenerRecetasPorNombreTag(@RequestParam(name="nombreTag") String nombreTag) {
        return ResponseEntity.ok(this.recetaService.buscarRecetasPorNombreDeTag(nombreTag));
    }

    @Operation(summary = "Obtiene la lista de recetas por varios nombres de tag.")
    @GetMapping("/recetasByTags")
    public ResponseEntity<List<RecetaDTO>> buscarRecetasPorNombreDeTags(@RequestParam(name="tag") List<String> tags) {
        return ResponseEntity.ok(this.recetaService.buscarRecetasPorNombreDeTags(tags));
    }
    @Operation(summary = "Obtiene la lista de tags de una receta por su id.")
    @GetMapping("/tags")
    public ResponseEntity<List<TagRecetaDTO>> obtenerTagsPorIdReceta(@RequestParam(name="idReceta") Long idReceta) {
        return ResponseEntity.ok(this.recetaService.obtenerTagsRecetaPorId(idReceta));
    }

//PASOS_RECETA
    @Operation(summary = "Añade el paso de elaboración de una receta por orden.")
    @PostMapping("/add-paso")
    public ResponseEntity<PasoRecetaDTO> crearPasoDeReceta(@RequestBody PasoRecetaView recetaView){
        PasoRecetaDTO nuevaReceta = this.pasoRecetaService.addPasoReceta(recetaView);
        return ResponseEntity.status(201).body(nuevaReceta);
    }

    @Operation(summary = "Obtiene los pasos para hacer la receta, por id de receta y en orden de paso.")
    @GetMapping("/pasos")
    public ResponseEntity<List<PasoRecetaDTO>> obtenerPasosRecetaPorIdReceta(@RequestParam(name="idReceta") Long idReceta) {
        return ResponseEntity.ok(this.pasoRecetaService.obtenerPasosRecetaPorId(idReceta));
    }

    @Operation(summary = "Actualiza el paso de una receta por id de receta y número de orden del paso.")
    @PutMapping("/update-paso")
    public ResponseEntity<PasoRecetaDTO> updatePasoReceta(@RequestBody PasoRecetaView pasoRecetaView) {
        return ResponseEntity.ok(this.pasoRecetaService.updatePasoReceta(pasoRecetaView));
    }

    @Operation(summary = "Elimina el paso de una receta por id de receta y número de orden del paso.")
    @DeleteMapping("/delete-paso")
    public ResponseEntity<String> deletePasoReceta(@RequestBody PasoRecetaView pasoRecetaView) {
        if(this.pasoRecetaService.deletePasoReceta(pasoRecetaView)){
            return ResponseEntity.ok("Paso de la receta eliminado correctamente");
        }else{
            return ResponseEntity.ok("Paso de la receta no eliminado. No se ha encontrado este paso");
        }
    }










}
