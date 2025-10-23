package com.daw.celiblog.service;

import com.daw.celiblog.dto.ComentarioDTO;
import com.daw.celiblog.dto.PasoRecetaDTO;
import com.daw.celiblog.dto.RecetaDTO;
import com.daw.celiblog.dto.TagRecetaDTO;

import java.util.List;

public interface RecetaService {
    List<RecetaDTO> obtenerTodos();
    RecetaDTO obtenerPorId(Long id);
    RecetaDTO crear(RecetaDTO dto);
    RecetaDTO actualizar(Long id, RecetaDTO dto);
    void eliminar(Long id);
    List<ComentarioDTO> obtenerComentariosDeRecetaPorIdReceta(Long idReceta);
    List<TagRecetaDTO> obtenerTagsRecetaPorId(Long idReceta);
    List<PasoRecetaDTO> obtenerPasosRecetaPorId(Long idReceta);

    RecetaDTO crearReceta(RecetaDTO recetaDTO);

}
