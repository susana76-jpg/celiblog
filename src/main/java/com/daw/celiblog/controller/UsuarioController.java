package com.daw.celiblog.controller;

import com.daw.celiblog.dto.PasoRecetaDTO;
import com.daw.celiblog.dto.RecetaDTO;
import com.daw.celiblog.dto.TagRecetaDTO;
import com.daw.celiblog.dto.UsuarioDTO;
import com.daw.celiblog.service.RecetaService;
import com.daw.celiblog.service.TagRecetaService;
import com.daw.celiblog.service.UsuarioService;
import com.daw.celiblog.service.impl.UsuarioServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {


    @Autowired
    private UsuarioService usuarioService;


    @Operation(summary = "Todos los usuarios.")
    @GetMapping("/all")
    public ResponseEntity<List<UsuarioDTO>> obtenerTodos() {
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    @Operation(summary = "Actualizar rol de usuario.")
    @PutMapping("/update-rol")
    public ResponseEntity<UsuarioDTO> updateRol(@RequestParam(value="idUsuario")Long idUsuario, @RequestParam(value="idNuevoRol")Long idNuevoRol) {
        return ResponseEntity.ok(usuarioService.actualizarRol(idUsuario, idNuevoRol));
    }

    @Operation(summary = "Añadir nuevo usuario")
    @PostMapping("/add")
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.crear(usuarioDTO));
    }

    @Operation(summary = "Eliminar usuario por su id.")
    @DeleteMapping("/idUsuario")
    public ResponseEntity<String> eliminarById(@RequestParam(value="idUsuario")Long idUsuario) {
        if(usuarioService.eliminar(idUsuario)){
            return ResponseEntity.ok("Usuario eliminado correctamente");
        }else{
            return ResponseEntity.ok("Usuario no eliminado. No se ha encontrado el usuario por el id "+idUsuario);
        }

    }











}
