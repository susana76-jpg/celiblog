package com.daw.celiblog.controller;

import com.daw.celiblog.db.entity.VistaRecetaIngredientes;
import com.daw.celiblog.dto.*;
import com.daw.celiblog.enums.EstadoValidacionEnum;
import com.daw.celiblog.enums.TipoComidaEnum;
import com.daw.celiblog.service.RecetaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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

    //PUBLICO
    @Operation(summary = "PÚBLICO: Obtiene todas las recetas registradas.")
    @GetMapping("public/all")
    public ResponseEntity<List<RecetaDTO>> all(Authentication authentication) {
        return ResponseEntity.ok(recetaService.getAll(authentication));
    }
    @Operation(summary = "PÚBLICO: Obtiene la receta por su id.")
    @GetMapping("public/byId")
    public ResponseEntity<?> getById(Authentication authentication, @RequestParam(name="id") Long id) {
        RecetaDTO receta = this.recetaService.getById(authentication, id);
        if(receta != null){
            return ResponseEntity.ok(receta);
        }else{
            return ResponseEntity.badRequest().body("No se existe el objeto.");
        }
    }
    @Operation(summary = "PÚBLICO: Obtiene las recetas por número de comensales.")
    @GetMapping("public/comensales")
    public ResponseEntity<List<RecetaDTO>> getRecetaByNumComensales(Authentication authentication, @RequestParam(name="numComensales") int numComensales) {
        return ResponseEntity.ok(this.recetaService.getByNumComensales(authentication, numComensales));
    }
    @Operation(summary = "PÚBLICO: Obtiene las recetas por valoracion.")
    @GetMapping("public/valoracion")
    public ResponseEntity<List<RecetaDTO>> getRecetaByValoracion(Authentication authentication, @RequestParam(name="valoracion") int valoracion) {
        return ResponseEntity.ok(this.recetaService.getByValoracion(authentication, valoracion));
    }
    @Operation(summary = "PÚBLICO: Obtiene las recetas por tipo de comida (desayuno, cena, brunch,...).")
    @GetMapping("public/tipo")
    public ResponseEntity<List<RecetaDTO>> getRecetaByTipoComida(Authentication authentication, @RequestParam(name="tipo") TipoComidaEnum tipo) {
        return ResponseEntity.ok(this.recetaService.getByTipoComida(authentication, tipo));
    }
    @Operation(summary = "PÚBLICO: Obtiene los ingredientes, cantidad y unidades de los ingredientes de una receta, por el id de receta.")
    @GetMapping("public/ingredientes")
    public ResponseEntity<List<VistaRecetaIngredientes>> getIngredientesByIdReceta(@RequestParam(name="idReceta") Long idReceta) {
        return ResponseEntity.ok(this.recetaService.getIngredientesByIdReceta(idReceta));
    }
    @Operation(summary = "PÚBLICO: Obtiene todas las recetas publicadas por los usuarios y aprobadas para su publicación por un administrador.")
    @GetMapping("public/estado-aprobada")
    public ResponseEntity<List<RecetaDTO>> getRecetasEstadoAprobado() {
        return ResponseEntity.ok(recetaService.getRecetasEstadoAprobado());
    }
    @Operation(summary = "PÚBLICO: Obtiene las recetas filtradas por título, subtítulo, descripción, nombre de ingrediente o tipo de comida")
    @GetMapping("public/buscar")
    public ResponseEntity<List<RecetaDTO>> buscar(
            Authentication authentication,
            @RequestParam(required = false, name="keyword") String keyword,
            @RequestParam(required = false, name="tipoComida") List<TipoComidaEnum> tipoComida
    ) {
        return ResponseEntity.ok(recetaService.buscarVista(authentication, keyword, tipoComida));
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @Operation(summary = "PRIVADO: Elimina una receta por su id. Elimina también los ingredientes y pasos de la receta.")
    @DeleteMapping("/deleteById")
    public ResponseEntity<String> deleteById(Authentication authentication, @RequestParam(name="id") Long id){
        if(this.recetaService.deleteById(authentication, id)){
            return ResponseEntity.status(200).body("Se ha eliminado la receta, sus ingredientes y pasos correctamente.");
        }else{
            return ResponseEntity.status(300).body("No existe la receta a eliminar.");
        }
    }

    //PRIVADO - GESTIÓN ESTADO DE PUBLICACIÓN
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @Operation(summary = "PRIVADO - GESTIÓN ESTADO DE PUBLICACIÓN: Obtiene todas las recetas publicadas por los usuarios y pendientes de validar para su publicación por un administrador o editor.")
    @GetMapping("/estado-pendiente")
    public ResponseEntity<List<RecetaDTO>> getRecetasEstadoPendiente(Authentication authentication) {
        return ResponseEntity.ok(recetaService.getRecetasEstadoPendiente(authentication));
    }
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @Operation(summary = "PRIVADO - GESTIÓN ESTADO DE PUBLICACIÓN: Obtiene todas las recetas publicadas por los usuarios y rechazadas para su publicación por un administrador.")
    @GetMapping("/estado-rechazado")
    public ResponseEntity<List<RecetaDTO>> getRecetasEstadoRechazado(Authentication authentication) {
        return ResponseEntity.ok(recetaService.getRecetasEstadoRechazado(authentication));
    }
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @Operation(summary = "PRIVADO - GESTIÓN ESTADO DE PUBLICACIÓN: Actualiza el estado de publicación de una receta (PENDIENTE, APROBADO, RECHAZADO")
    @PutMapping("/update-estado-publicacion")
    ResponseEntity<RecetaDTO> updateEstadoPublicacionReceta(Authentication authentication, @RequestParam(name="idReceta") Long idReceta, @RequestParam(name="estado") EstadoValidacionEnum estado) {
        return ResponseEntity.ok(recetaService.updateEstadoPublicacionReceta(authentication, idReceta, estado));
    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','EDITOR','VISITOR')")
    @Operation(summary = "PROTEGIDO: Añade una receta.")
    @PostMapping("/add")
    public ResponseEntity<?> add(Authentication authentication, @RequestBody RecetaView recetaView){
        RecetaDTO nuevaReceta = this.recetaService.add(authentication, recetaView);
        if(nuevaReceta != null){
            return ResponseEntity.ok(nuevaReceta);
        }else{
            return ResponseEntity.badRequest().body("No se añadió el objeto.");
        }
    }
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','EDITOR','VISITOR')")
    @Operation(summary = "PROTEGIDO: Actualiza una receta.")
    @PutMapping("/update")
    public ResponseEntity<?> update(Authentication authentication, @RequestBody RecetaView recetaView){
        RecetaDTO nuevaReceta = this.recetaService.update(authentication, recetaView);
        if(nuevaReceta != null){
            return ResponseEntity.ok(nuevaReceta);
        }else{
            return ResponseEntity.badRequest().body("No se actualizó el objeto.");
        }
    }


}
