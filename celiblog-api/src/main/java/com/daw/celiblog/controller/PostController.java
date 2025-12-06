package com.daw.celiblog.controller;

import com.daw.celiblog.dto.PostDTO;
import com.daw.celiblog.dto.PostView;
import com.daw.celiblog.dto.RestauranteDTO;
import com.daw.celiblog.dto.RestauranteView;
import com.daw.celiblog.enums.EstadoValidacionEnum;
import com.daw.celiblog.service.PostService;
import com.daw.celiblog.service.RestauranteService;
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


    //GESTIÓN ESTADO DE PUBLICACIÓN
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @Operation(summary = "ADMINISTRADOR - GESTIÓN ESTADO DE PUBLICACIÓN: Obtiene todos los post publicados por los usuarios y pendientes de validar para su publicación por un administrador.")
    @GetMapping("/estado-pendiente")
    public ResponseEntity<List<PostDTO>> getPostEstadoPendiente() {
        return ResponseEntity.ok(this.postService.getPostEstadoPendiente());
    }

    @Operation(summary = "Obtiene todos los post publicados por los usuarios y aprobados para su publicación por un administrador.")
    @GetMapping("public/estado-aprobada")
    public ResponseEntity<List<PostDTO>> getPostEstadoAprobado() {
        return ResponseEntity.ok(this.postService.getPostEstadoAprobado());
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @Operation(summary = "ADMINISTRADOR - GESTIÓN ESTADO DE PUBLICACIÓN: Obtiene todos los restaurantes publicados por los usuarios  y rechazados para su publicación por un administrador.")
    @GetMapping("/estado-rechazado")
    public ResponseEntity<List<PostDTO>> getPostEstadoRechazado() {
        return ResponseEntity.ok(this.postService.getPostEstadoRechazado());
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @Operation(summary = "ADMINISTRADOR - GESTIÓN ESTADO DE PUBLICACIÓN: Actualiza el estado de publicación de un post (PENDIENTE, APROBADO, RECHAZADO")
    @PutMapping("/update-estado-publicacion")
    ResponseEntity<PostDTO> updateEstadoPublicacionPost(@RequestParam(name="idPost") Long idPost, @RequestParam(name="estado") EstadoValidacionEnum estado) {
        return ResponseEntity.ok(this.postService.updateEstadoPublicacionPost(idPost, estado));
    }


}
