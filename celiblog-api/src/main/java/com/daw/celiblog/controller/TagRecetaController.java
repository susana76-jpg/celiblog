package com.daw.celiblog.controller;

import com.daw.celiblog.dto.RecetaDTO;
import com.daw.celiblog.dto.TagRecetaDTO;
import com.daw.celiblog.dto.TagRecetaView;
import com.daw.celiblog.service.RecetaService;
import com.daw.celiblog.service.TagRecetaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Tag Recetas", description = "Operaciones relacionadas con los tags de recetas.")
@RequestMapping("/api/tag-receta")
public class TagRecetaController {
    @Autowired
    TagRecetaService tagRecetaService;
    @Autowired
    RecetaService recetaService;

    @Operation(summary = "Obtiene un listado de los nombres de todos los tags de recetas existentes.")
    @GetMapping("public/all")
    public ResponseEntity<List<String>> obtenerPorId() {
        return ResponseEntity.ok(tagRecetaService.obtenerTodosNombresTags());
    }

    @Operation(summary = "Añade tag a una receta..")
    @PostMapping("/add")
    public ResponseEntity<TagRecetaDTO> crearTagReceta(@RequestBody TagRecetaView tagRecetaView){
        TagRecetaDTO nuevoTagReceta = this.tagRecetaService.crearTagReceta(tagRecetaView);
        return ResponseEntity.status(201).body(nuevoTagReceta);
    }

    @Operation(summary = "Elimina un tag de una receta por el id del tag.")
    @DeleteMapping("/delete")
    public ResponseEntity<String> eliminarPorIdTag(@RequestParam(value="idTag")Long idTag) {
        if (this.tagRecetaService.eliminarByIdTag(idTag)) {
            return ResponseEntity.ok("Tag eliminado correctamente.");
        } else {
            return ResponseEntity.ok("Tag no eliminado. No se ha encontrado el tag por el id " + idTag);
        }
    }

    @Operation(summary = "Elimina el tag de una receta por el id de la receta y la descripción del tag.")
    @DeleteMapping("/delete-byIdReceta")
    public ResponseEntity<String> eliminarPorIdRecetaAndTagNombre(@RequestParam(value="idReceta")Long idReceta, @RequestParam(value="nombreTag")String nombreTag) {
        if (this.tagRecetaService.eliminarByIdRecetaAndNombreTag(idReceta, nombreTag)) {
            return ResponseEntity.ok("Tag eliminado correctamente.");
        } else {
            return ResponseEntity.ok("Tag no eliminado. No se ha encontrado, en la receta con id "+idReceta+ " o el tag con nombre  " + nombreTag+ " en la receta");
        }
    }

    @Operation(summary = "Obtiene la lista de recetas por nombre de un tag.")
    @GetMapping("public/recetasByTag")
    public ResponseEntity<List<RecetaDTO>> obtenerRecetasPorNombreTag(@RequestParam(name="nombreTag") String nombreTag) {
        return ResponseEntity.ok(this.recetaService.buscarRecetasPorNombreDeTag(nombreTag));
    }

    @Operation(summary = "Obtiene la lista de recetas por varios nombres de tag.")
    @GetMapping("public/recetasByTags")
    public ResponseEntity<List<RecetaDTO>> buscarRecetasPorNombreDeTags(@RequestParam(name="tag") List<String> tags) {
        return ResponseEntity.ok(this.recetaService.buscarRecetasPorNombreDeTags(tags));
    }
   /* @Operation(summary = "Obtiene la lista de tags de una receta por su id.")
    @GetMapping("/tags")
    public ResponseEntity<List<TagRecetaDTO>> obtenerTagsPorIdReceta(@RequestParam(name="idReceta") Long idReceta) {
        return ResponseEntity.ok(this.recetaService.obtenerTagsRecetaPorId(idReceta));
    }*/



}
