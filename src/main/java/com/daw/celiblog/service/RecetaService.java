package com.daw.celiblog.service;

import com.daw.celiblog.db.entity.Receta;
import com.daw.celiblog.dto.*;
import org.springframework.data.repository.query.Param;

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

    List<RecetaDTO> buscarRecetasPorNombreDeTag(String nombreTag);

    List<RecetaDTO> buscarRecetasPorNombreDeTags(List<String> tags);

}
