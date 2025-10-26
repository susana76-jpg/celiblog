package com.daw.celiblog.service;

import com.daw.celiblog.dto.RecetaDTO;
import com.daw.celiblog.dto.TagRecetaDTO;
import com.daw.celiblog.dto.TagRecetaView;

import java.util.List;

public interface TagRecetaService {
    List<String> obtenerTodosNombresTags();

    TagRecetaDTO crearTagReceta(TagRecetaView tagRecetaView);

    boolean eliminarByIdTag(Long idTag);
    boolean eliminarByIdRecetaAndNombreTag(Long idReceta, String nombreTag);




}
