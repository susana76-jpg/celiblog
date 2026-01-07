package com.daw.celiblog.controller;

import com.daw.celiblog.dto.UsuarioDTO;
import com.daw.celiblog.dto.UsuarioView;
import com.daw.celiblog.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Usuarios", description = "Operaciones relacionadas con usuarios.")
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @Operation(summary = "PRIVADO: Obtiene un usuario por id.")
    @GetMapping("/byId")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorId(@RequestParam(value="idUsuario")Long idUsuario) {
        return ResponseEntity.ok(this.usuarioService.obtenerUsuarioPorId(idUsuario));
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @Operation(summary = "PRIVADO: Obtiene el listado de todos los usuarios.")
    @GetMapping("/all")
    public ResponseEntity<List<UsuarioDTO>> obtenerTodos() {
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @Operation(summary = "PRIVADO: Actualiza el rol del usuario por su id, (1-ADMINISTRADOR, 2-EDITOR, 3-VISITOR).")
    @PutMapping("/update-rol-usuario")
    public ResponseEntity<UsuarioDTO> updateRol(@RequestParam(value="idUsuario")Long idUsuario, @RequestParam(value="idNuevoRol")Long idNuevoRol) {
        return ResponseEntity.ok(this.usuarioService.actualizarRol(idUsuario, idNuevoRol));
    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','EDITOR','VISITOR')")
    @Operation(summary = "PROTEGIDO: Actualiza el nombre del usuario.")
    @PutMapping("/update-nombre-usuario")
    public ResponseEntity<?> updateNombre(Authentication authentication, @RequestParam(value="nombre")String nombre) {
        UsuarioDTO usuario = this.usuarioService.actualizarNombre(authentication, nombre);
        if(usuario != null){
            return ResponseEntity.ok(usuario);
        }else{
            return ResponseEntity.badRequest().body("No se actualizó el nombre del usuario.");
        }
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @Operation(summary = "PRIVADO: Añade un nuevo usuario.")
    @PostMapping("/add")
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody UsuarioView usuarioView) {

        return ResponseEntity.ok(this.usuarioService.crear(usuarioView));
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @Operation(summary = "PRIVADO: Elimina el usuario por su id. * IMPORTANTE: Este end-point hará que todos los comentarios hechos por el usuario se eliminen también.")
    @DeleteMapping("/delete")
    public ResponseEntity<String> eliminarById(Authentication authentication, @RequestParam(value="idUsuario")Long idUsuario) {
        if(this.usuarioService.eliminar(authentication, idUsuario)){
            return ResponseEntity.ok("Usuario eliminado correctamente. IMPORTANTE: Todos los elementos relacionados con este usuario también han sido eliminados");
        }else{
            return ResponseEntity.ok("Usuario no eliminado. No se ha encontrado el usuario por el id "+idUsuario);
        }

    }











}
