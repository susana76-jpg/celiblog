package com.daw.celiblog.service;

import com.daw.celiblog.dto.PostDTO;
import com.daw.celiblog.dto.TagRecetaDTO;
import com.daw.celiblog.dto.TagRecetaView;

import java.util.List;

public interface TagPostService {
    List<String> obtenerTodosNombresTags();
    PostDTO crearTagPost(TagRecetaView tagRecetaView);
    boolean eliminarByIdTag(Long idTag);
    boolean eliminarByIdPostAndNombreTag(Long idPost, String nombreTag);




}
