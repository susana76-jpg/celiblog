package com.daw.celiblog.service;

import com.daw.celiblog.dto.UsuarioView;
import com.daw.celiblog.dto.UsuarioDTO;

import java.util.List;

    public interface UsuarioService {
    UsuarioDTO obtenerUsuarioPorId(Long idUsuario);
    List<UsuarioDTO> obtenerTodos();
    UsuarioDTO obtenerPorId(Long id);
    UsuarioDTO crear(UsuarioView dto);
    UsuarioDTO actualizarRol(Long idUsuario, Long idRol);
    boolean eliminar(Long id);
}
