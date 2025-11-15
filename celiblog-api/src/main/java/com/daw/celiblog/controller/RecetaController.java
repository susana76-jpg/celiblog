package com.daw.celiblog.controller;

import com.daw.celiblog.dto.*;
import com.daw.celiblog.enums.EstadoValidacion;
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


    //GESTIÓN ESTADO DE PUBLICACIÓN
    @Operation(summary = "GESTIÓN ESTADO DE PUBLICACIÓN: Obtiene todas las recetas publicadas por los usuarios y pendientes de validar para su publicación por un administrador.")
    @GetMapping("/estado-pendiente")
    public ResponseEntity<List<RecetaDTO>> getRecetasEstadoPendiente() {
        return ResponseEntity.ok(recetaService.getRecetasEstadoPendiente());
    }

    @Operation(summary = "GESTIÓN ESTADO DE PUBLICACIÓN: Obtiene todas las recetas publicadas por los usuarios y aprobadas para su publicación por un administrador.")
    @GetMapping("/estado-aprobada")
    public ResponseEntity<List<RecetaDTO>> getRecetasEstadoAprobado() {
        return ResponseEntity.ok(recetaService.getRecetasEstadoAprobado());
    }

    @Operation(summary = "GESTIÓN ESTADO DE PUBLICACIÓN: Obtiene todas las recetas publicadas por los usuarios y rechazadas para su publicación por un administrador.")
    @GetMapping("/estado-rechazado")
    public ResponseEntity<List<RecetaDTO>> getRecetasEstadoRechazado() {
        return ResponseEntity.ok(recetaService.getRecetasEstadoRechazado());
    }


    @Operation(summary = "GESTIÓN ESTADO DE PUBLICACIÓN: Actualiza el estado de publicación de una receta (PENDIENTE, APROBADO, RECHAZADO")
    @PutMapping("/update-estado-publicacion")
    ResponseEntity<RecetaDTO> updateEstadoPublicacionReceta(@RequestParam(name="idReceta") Long idReceta, @RequestParam(name="estado")EstadoValidacion estado) {
        return ResponseEntity.ok(recetaService.updateEstadoPublicacionReceta(idReceta, estado));
    }
    ;







}
