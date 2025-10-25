package com.daw.celiblog.service;

import com.daw.celiblog.dto.RecetaDTO;
import com.daw.celiblog.dto.UsuarioDTO;

import java.util.List;

public interface UsuarioService {
    List<UsuarioDTO> obtenerTodos();
    UsuarioDTO obtenerPorId(Long id);
    UsuarioDTO crear(UsuarioDTO dto);
    UsuarioDTO actualizarRol(Long idUsuario, Long idRol);
    boolean eliminar(Long id);
}
