package com.daw.celiblog.service;

import com.daw.celiblog.db.entity.PasoReceta;
import com.daw.celiblog.db.entity.Receta;
import com.daw.celiblog.db.entity.VistaRecetaIngredientes;
import com.daw.celiblog.dto.*;
import com.daw.celiblog.enums.EstadoValidacion;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface RecetaService {
    List<RecetaDTO> obtenerTodos();
    RecetaDTO obtenerPorId(Long id);
    RecetaDTO crear(RecetaDTO dto);
    RecetaDTO actualizar(Long id, RecetaDTO dto);
    void eliminar(Long id);
    List<ComentarioDTO> obtenerComentariosDeRecetaPorIdReceta(Long idReceta);
    List<TagRecetaDTO> obtenerTagsRecetaPorId(Long idReceta);
    RecetaDTO crearReceta(RecetaDTO recetaDTO);
    List<RecetaDTO> buscarRecetasPorNombreDeTag(String nombreTag);
    List<RecetaDTO> buscarRecetasPorNombreDeTags(List<String> tags);
    boolean deleteReceta(Long id);


    //gestión de aprobación de publicaciones de recetas
    List<RecetaDTO> getRecetasEstadoPendiente();
    List<RecetaDTO> getRecetasEstadoAprobado();
    List<RecetaDTO> getRecetasEstadoRechazado();
    RecetaDTO updateEstadoPublicacionReceta(Long idReceta, EstadoValidacion estado);
    List<VistaRecetaIngredientes> getIngredientesByIdReceta(Long idReceta);



}
