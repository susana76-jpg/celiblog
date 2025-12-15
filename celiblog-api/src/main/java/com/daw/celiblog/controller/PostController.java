package com.daw.celiblog.controller;

import com.daw.celiblog.dto.PostDTO;
import com.daw.celiblog.dto.PostView;
import com.daw.celiblog.service.PostService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Post y publicaciones", description = "Operaciones relacionadas con los post y publicaciones en el blog.")
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Operation(summary = "PÚBLICO: Obtiene todos los post.")
    @GetMapping("public/all")
    public ResponseEntity<List<PostDTO>> all(Authentication authentication) {
        return ResponseEntity.ok(this.postService.all(authentication));
    }
    @Operation(summary = "PÚBLICO: Obtiene un post por su id.")
    @GetMapping("public/byId")
    public ResponseEntity<?> getById(Authentication authentication, @RequestParam(name="id") Long id) {
        if(postService.getById(authentication, id) != null){
            return ResponseEntity.ok(postService.getById(authentication, id));
        }else{
            return ResponseEntity.badRequest().body("No existe objeto.");
        }
    }
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','EDITOR','VISITOR')")
    @Operation(summary = "PROTEGIDO: Añade un post.")
    @PostMapping("/add")
    public ResponseEntity<?> add(Authentication authentication, @RequestBody PostView postView) throws JsonProcessingException {
        PostDTO nuevoPost = this.postService.add(authentication, postView);
        if(nuevoPost != null){
            return ResponseEntity.ok(nuevoPost);
        }else{
            return ResponseEntity.badRequest().body("No se añadió el objeto.");
        }
    }
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @Operation(summary = "ADMINISTRADOR: Elimina un post por su id. Elimina también los tags vinculados al post eliminado.")
    @DeleteMapping("/deleteById")
    public ResponseEntity<String> delete(@RequestParam(name="id") Long id){
        if(this.postService.deleteById(id)){
            return ResponseEntity.status(200).body("Se ha eliminado el restaurante.");
        }else{
            return ResponseEntity.status(300).body("No existe el restaurante a eliminar.");
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','EDITOR','VISITOR')")
    @Operation(summary = "PROTEGIDO: Actualiza los datos de un post.")
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody PostView postView, @RequestParam(name="idPost") Long idPost){
        PostDTO post = this.postService.update(idPost, postView);
        if(post != null){
            return ResponseEntity.ok(post);
        }else{
            return ResponseEntity.badRequest().body("No se actualizó el objeto.");
        }
    }

    @Operation(summary = "PÚBLICO: Obtiene los post por palabra contenida en su título, subtítulo o contenido")
    @GetMapping("public/buscar")
    public ResponseEntity<List<PostDTO>> buscar(
            Authentication authentication,
            @RequestParam(required = false, name="keyword") String keyword
    ) {
        return ResponseEntity.ok(this.postService.buscar(authentication, keyword));
    }





}
