package com.daw.celiblog.controller;

import com.daw.celiblog.dto.FavoritoDTO;
import com.daw.celiblog.dto.FavoritoView;
import com.daw.celiblog.dto.UsuarioDTO;
import com.daw.celiblog.dto.UsuarioView;
import com.daw.celiblog.service.FavoritoService;
import com.daw.celiblog.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Favoritos", description = "Operaciones relacionadas con los favoritos del usuario.")
@RequestMapping("/api/favoritos")
public class FavoritoController {

    @Autowired
    private FavoritoService favoritoService;


    @Operation(summary = "Obtiene el listado de favoritos de restaurantes del usuario, por id de usuario.")
    @GetMapping("/restaurantes")
    public ResponseEntity<List<FavoritoDTO>> obtenerFavoritosRestaurante(@RequestParam(name="idUsuario") Long idUsuario, Authentication authentication) {
        String email = authentication.getName(); // el "sub" del JWT (el email)

        return ResponseEntity.ok(this.favoritoService.getFavoritosRestaurantesByIdUsuario(idUsuario));
    }


    @Operation(summary = "Añade un favorito.")
    @PostMapping("/add")
    public ResponseEntity<FavoritoDTO> crearFavorito(@RequestBody FavoritoView favoritoView){
        return ResponseEntity.status(200).body(this.favoritoService.addFavorito(favoritoView));
    }

    @Operation(summary = "Elimina un favorito por su id.")
    @DeleteMapping("/deleteById")
    public ResponseEntity<String> deleteReceta(@RequestParam(name="idFavorito") Long idFavorito){
        if(this.favoritoService.deleteFavorito(idFavorito)){
            return ResponseEntity.status(200).body("Se ha eliminado el favorito con id "+idFavorito);
        }else{
            return ResponseEntity.status(300).body("No existe el favorito a eliminar.");
        }

    }












}
