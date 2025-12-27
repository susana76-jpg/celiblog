package com.daw.celiblog.service;

import com.daw.celiblog.dto.UsuarioDTO;
import com.daw.celiblog.dto.UsuarioView;
import org.springframework.security.core.Authentication;

import java.util.List;

    public interface UsuarioService {
    UsuarioDTO obtenerUsuarioPorId(Long idUsuario);
    UsuarioDTO obtenerUsuarioPorEmail(String email);
    List<UsuarioDTO> obtenerTodos();
    UsuarioDTO obtenerPorId(Long id);
    UsuarioDTO crear(UsuarioView dto);
    UsuarioDTO actualizarRol(Long idUsuario, Long idRol);
    UsuarioDTO actualizarNombre(Authentication authentication, String nombre);
    boolean eliminar(Long id);
    Long getIdUsuarioLogado(String email);

}
