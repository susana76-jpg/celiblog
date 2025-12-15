package com.daw.celiblog.service;

import com.daw.celiblog.dto.*;
import com.daw.celiblog.enums.EstadoValidacionEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface PostService {
    List<PostDTO> all(Authentication authentication);
    PostDTO getById(Authentication authentication, Long id);
    PostDTO add(Authentication authentication, PostView postView);
    PostDTO update(Long id, PostView postView);
    boolean deleteById(Long id);
    List<PostDTO> buscar(Authentication authentication, String keyword);

}
