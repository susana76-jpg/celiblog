package com.daw.celiblog.service;

import com.daw.celiblog.dto.TagRecetaDTO;
import com.daw.celiblog.dto.TagRecetaView;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface TagRecetaService {
    List<String> obtenerTodosNombresTags(Authentication authentication);

    TagRecetaDTO crearTagReceta(TagRecetaView tagRecetaView);

    boolean eliminarByIdTag(Long idTag);
    boolean eliminarByIdRecetaAndNombreTag(Long idReceta, String nombreTag);




}
