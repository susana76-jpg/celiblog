package com.daw.celiblog.controller;

import com.daw.celiblog.dto.PasoRecetaDTO;
import com.daw.celiblog.dto.PasoRecetaView;
import com.daw.celiblog.dto.RecetaDTO;
import com.daw.celiblog.service.PasoRecetaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Tag(name = "Pasos receta", description = "Operaciones relacionadas con los pasos de elaboración en una receta.")
@RequestMapping("/api/paso-receta")
public class PasoRecetaController {
    @Autowired
    PasoRecetaService pasoRecetaService;

    @Operation(summary = "PÚBLICO: Obtiene los pasos para hacer la receta, por id de receta y en orden de paso.")
    @GetMapping("public/pasos")
    public ResponseEntity<List<PasoRecetaDTO>> obtenerPasosRecetaPorIdReceta(@RequestParam(name="idReceta") Long idReceta) {
        return ResponseEntity.ok(this.pasoRecetaService.obtenerPasosRecetaPorId(idReceta));
    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','EDITOR','VISITOR')")
    @Operation(summary = "PROTEGIDO: Añade el paso de elaboración de una receta por orden.")
    @PostMapping("/add")
    public ResponseEntity<PasoRecetaDTO> crearPasoDeReceta(@RequestBody PasoRecetaView recetaView){
        PasoRecetaDTO nuevaReceta = this.pasoRecetaService.addPasoReceta(recetaView);
        return ResponseEntity.status(201).body(nuevaReceta);
    }
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','EDITOR','VISITOR')")
    @Operation(summary = "PROTEGIDO: Actualiza el paso de una receta por id de receta y número de orden del paso.")
    @PutMapping("/update-paso")
    public ResponseEntity<PasoRecetaDTO> updatePasoReceta(@RequestBody PasoRecetaView pasoRecetaView) {
        return ResponseEntity.ok(this.pasoRecetaService.updatePasoReceta(pasoRecetaView));
    }
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','EDITOR','VISITOR')")
    @Operation(summary = "PROTEGIDO: Elimina el paso de una receta por id de receta y número de orden del paso.")
    @DeleteMapping("/delete-paso")
    public ResponseEntity<String> deletePasoReceta(@RequestBody PasoRecetaView pasoRecetaView) {
        if(this.pasoRecetaService.deletePasoReceta(pasoRecetaView)){
            return ResponseEntity.ok("Paso de la receta eliminado correctamente");
        }else{
            return ResponseEntity.ok("Paso de la receta no eliminado. No se ha encontrado este paso");
        }
    }

}
