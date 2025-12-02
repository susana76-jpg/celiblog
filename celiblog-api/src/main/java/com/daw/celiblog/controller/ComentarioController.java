package com.daw.celiblog.controller;

import com.daw.celiblog.dto.ComentarioDTO;
import com.daw.celiblog.dto.ComentarioView;
import com.daw.celiblog.dto.FavoritoDTO;
import com.daw.celiblog.dto.FavoritoView;
import com.daw.celiblog.enums.ObjetoEnum;
import com.daw.celiblog.service.ComentarioService;
import com.daw.celiblog.service.FavoritoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Operation(summary = "PÚBLICO: Obtiene todos los comentarios referidos a un objeto (RESTAURANTE, RECETAS,POST), ordenados por fecha de publicación, por el id de objeto comentado.")
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
    public ResponseEntity<ComentarioDTO> addComentario(
            Authentication authentication,
            @RequestBody ComentarioView comentarioView, @RequestParam(name=("objetoComentado")) ObjetoEnum objetoComentado){
        return ResponseEntity.status(200).body(this.comentarioService.addComentario(authentication,comentarioView,objetoComentado));
    }


    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @Operation(summary = "PRIVADO: Actualiza un comentario.")
    @PutMapping("/update")
    public ResponseEntity<ComentarioDTO> updateComentario(
            Authentication authentication,
            @RequestBody ComentarioView comentarioView, @RequestParam(name=("idComentario")) Long idComentario){

        ComentarioDTO comentario = this.comentarioService.updateComentario(authentication,comentarioView,idComentario);
        if (comentario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null);
        }
        return ResponseEntity.status(200).body(this.comentarioService.updateComentario(authentication,comentarioView,idComentario));
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
