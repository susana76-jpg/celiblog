package com.daw.celiblog.service;

import com.daw.celiblog.db.repository.ComentarioRepository;
import com.daw.celiblog.dto.ComentarioDTO;
import com.daw.celiblog.dto.ComentarioView;
import com.daw.celiblog.dto.FavoritoView;
import com.daw.celiblog.enums.ObjetoEnum;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ComentarioService {

    List<ComentarioDTO> allComentariosByObject(Authentication authentication, ObjetoEnum objeto, Long idObjeto);

    ComentarioDTO addComentario(Authentication authentication, ComentarioView comentarioView, ObjetoEnum objetoEnum);



    ComentarioDTO updateComentario(Authentication authentication, ComentarioView comentarioView, Long idComentario);


    boolean  deleteById(Long idComentario);
}
