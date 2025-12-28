package com.daw.celiblog.controller;

import com.daw.celiblog.dto.IngredienteView;
import com.daw.celiblog.service.IngredienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Ingredientes de las recetas", description = "Operaciones relacionadas con los ingredientes vinculados a cada receta.")
@RequestMapping("/api/ingrediente")
public class IngredienteController {

    @Autowired
    private IngredienteService ingredienteService;

    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','EDITOR','VISITOR')")
    @Operation(summary = "PROTEGIDO: Añade ingredientes a una receta.")
    @PostMapping("/add")
    public ResponseEntity<?> add(
            Authentication authentication,
            @RequestBody List<IngredienteView> ingredientes){
        List<IngredienteView> ingredientesView = this.ingredienteService.add(authentication,ingredientes);
        if(ingredientesView != null){
            return ResponseEntity.ok(ingredientesView);
        }else{
            return ResponseEntity.badRequest().body("No se añadieron los objetos.");
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','EDITOR','VISITOR')")
    @Operation(summary = "PROTEGIDO: Actualiza un ingrediente.")
    @PutMapping("/update")
    public ResponseEntity<?> updateIngrediente(
            Authentication authentication,
            @RequestBody IngredienteView ingredienteView, @RequestParam(name=("idIngrediente")) Long idIngrediente){
        IngredienteView ingredient = this.ingredienteService.update(authentication, new IngredienteView(), idIngrediente);
        if(ingredient != null){
            return ResponseEntity.ok(ingredient);
        }else{
            return ResponseEntity.badRequest().body("No se actualizó el objeto.");
        }
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @Operation(summary = "PRIVADO: Elimina un ingrediente por su id.")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteById(@RequestParam(name="idReceta") Long idReceta,
                                             @RequestParam(name="idIngrediente") Long idIngrediente){
        if(this.ingredienteService.deleteById(idReceta, idIngrediente)){
            return ResponseEntity.status(200).body("Se ha eliminado el ingrediente con id "+idIngrediente);
        }else{
            return ResponseEntity.status(300).body("No existe el ingrediente a eliminar con id "+idIngrediente);
        }
    }












}
