package com.daw.celiblog.controller;

import com.daw.celiblog.dto.RecetaDTO;
import com.daw.celiblog.dto.TagRecetaDTO;
import com.daw.celiblog.dto.TagRecetaView;
import com.daw.celiblog.service.RecetaService;
import com.daw.celiblog.service.TagPostService;
import com.daw.celiblog.service.TagRecetaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Tag Post y publicaciones", description = "Operaciones relacionadas con los tags de post y publicaciones.")
@RequestMapping("/api/tag-post")
public class TagPostController {
    @Autowired
    TagPostService tagPostService;
    @Autowired
    RecetaService recetaService;

    @Operation(summary = "PÚBLICO: Obtiene un listado de los nombres de todos los tags de post existentes.")
    @GetMapping("public/all")
    public ResponseEntity<List<String>> getAll() {
        return ResponseEntity.ok(tagPostService.obtenerTodosNombresTags());
    }

   /* @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','EDITOR','VISITOR')")
    @Operation(summary = "PROTEGIDO: Añade tag a un post.")
    @PostMapping("/add")
    public ResponseEntity<TagRecetaDTO> add(@RequestBody TagRecetaView tagRecetaView){
        TagPostDTO nuevoTag = this.tagPostService.crearTagPost(tagRecetaView);
        return ResponseEntity.status(201).body(nuevoTag);
    }*/

    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','EDITOR','VISITOR')")
    @Operation(summary = "PROTEGIDO: Elimina un tag de un post por el id del tag.")
    @DeleteMapping("/delete")
    public ResponseEntity<String> eliminarPorIdTag(@RequestParam(value="idTag")Long idTag) {
        if (this.tagPostService.eliminarByIdTag(idTag)) {
            return ResponseEntity.ok("Tag eliminado correctamente.");
        } else {
            return ResponseEntity.ok("Tag no eliminado. No se ha encontrado el tag por el id " + idTag);
        }
    }
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','EDITOR','VISITOR')")
    @Operation(summary = "PROTEGIDO: Elimina el tag de un post por el id del post y la descripción del tag.")
    @DeleteMapping("/delete-byIdPost")
    public ResponseEntity<String> eliminarPorIdRecetaAndTagNombre(@RequestParam(value="idPost")Long idPost, @RequestParam(value="nombreTag")String nombreTag) {
        if (this.tagPostService.eliminarByIdPostAndNombreTag(idPost, nombreTag)) {
            return ResponseEntity.ok("Tag eliminado correctamente.");
        } else {
            return ResponseEntity.ok("Tag no eliminado. No se ha encontrado, el post con id "+idPost+ " o el tag con nombre  " + nombreTag+ " en el post");
        }
    }

    @Operation(summary = "PÚBLICO: Obtiene la lista de recetas por nombre de un tag.")
    @GetMapping("public/recetasByTag")
    public ResponseEntity<List<RecetaDTO>> obtenerRecetasPorNombreTag(Authentication authentication,  @RequestParam(name="nombreTag") String nombreTag) {
        return ResponseEntity.ok(this.recetaService.buscarRecetasPorNombreDeTag(authentication, nombreTag));
    }

    @Operation(summary = "PÚBLICO: Obtiene la lista de recetas por varios nombres de tag.")
    @GetMapping("public/recetasByTags")
    public ResponseEntity<List<RecetaDTO>> buscarRecetasPorNombreDeTags(Authentication authentication, @RequestParam(name="tag") List<String> tags) {
        return ResponseEntity.ok(this.recetaService.buscarRecetasPorNombreDeTags(authentication, tags));
    }

}
