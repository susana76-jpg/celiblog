package com.daw.celiblog.service;

import com.daw.celiblog.dto.PostDTO;
import com.daw.celiblog.dto.TagRecetaView;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface TagPostService {
    List<String> obtenerTodosNombresTags(Authentication authentication);
    PostDTO crearTagPost(TagRecetaView tagRecetaView);
    boolean eliminarByIdTag(Long idTag);
    boolean eliminarByIdPostAndNombreTag(Long idPost, String nombreTag);
    List<PostDTO> buscarPostsPorNombreDeTag(Authentication authentication, String nombreTag);
    List<PostDTO> buscarPostsPorNombreDeTags(Authentication authentication, List<String> tags);




}
