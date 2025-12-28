package com.daw.celiblog.controller;

import com.daw.celiblog.dto.ComentarioDTO;
import com.daw.celiblog.dto.ComentarioView;
import com.daw.celiblog.enums.ObjetoEnum;
import com.daw.celiblog.service.ComentarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Comentarios", description = "Operaciones relacionadas con los comentarios sobre RESTAURANTE, RECETAS Y POST de los usuarios.")
@RequestMapping("/api/comentario")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @Operation(summary = "PÚBLICO: Obtiene todos los comentarios referidos a un objeto (RESTAURANTE, RECETAS,POST), " +
            "ordenados por fecha de publicación, por el id de objeto comentado, en estado aprobado por el administrador.")
    @GetMapping("public/all")
    public ResponseEntity<List<ComentarioDTO>> allComentariosByObject(
            Authentication authentication,
            @RequestParam(value="objetoComentado")ObjetoEnum objetoComentado,
            @RequestParam(value="idObjetoComentado")Long idObjetoComentado) {

            return ResponseEntity.ok(this.comentarioService.allComentariosByObject(authentication,objetoComentado,idObjetoComentado));
    }
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','EDITOR','VISITOR')")
    @Operation(summary = "PROTEGIDO: Añade un comentario. Solo los usuarios logados podrán hacer comentarios sobre RESTAURANTE, RECETAS,POST")
    @PostMapping("/add")
    public ResponseEntity<?> add(
            Authentication authentication,
            @RequestBody ComentarioView comentarioView, @RequestParam(name=("objetoComentado")) ObjetoEnum objetoComentado){
        ComentarioDTO comentario = this.comentarioService.add(authentication,comentarioView,objetoComentado);
        if(comentario != null){
            return ResponseEntity.ok(comentario);
        }else{
            return ResponseEntity.badRequest().body("No se añadió el objeto.");
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','EDITOR','VISITOR')")
    @Operation(summary = "PROTEGIDO: Actualiza un comentario.")
    @PutMapping("/update")
    public ResponseEntity<?> updateComentario(
            Authentication authentication,
            @RequestBody ComentarioView comentarioView, @RequestParam(name=("idComentario")) Long idComentario){
        ComentarioDTO comentario = this.comentarioService.update(authentication, comentarioView, idComentario);
        if(comentario != null){
            return ResponseEntity.ok(comentario);
        }else{
            return ResponseEntity.badRequest().body("No se actualizó el objeto.");
        }
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @Operation(summary = "PRIVADO: Elimina un comentario por su id.")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteById(@RequestParam(name="idComentario") Long idComentario){
        if(this.comentarioService.deleteById(idComentario)){
            return ResponseEntity.status(200).body("Se ha eliminado el comentario con id "+idComentario);
        }else{
            return ResponseEntity.status(300).body("No existe la comentario a eliminar con id "+idComentario);
        }
    }












}
