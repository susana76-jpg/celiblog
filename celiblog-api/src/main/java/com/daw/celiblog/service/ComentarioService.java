package com.daw.celiblog.service;

import com.daw.celiblog.dto.ComentarioDTO;
import com.daw.celiblog.dto.ComentarioView;
import com.daw.celiblog.enums.ObjetoEnum;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ComentarioService {

    List<ComentarioDTO> allComentariosByObject(Authentication authentication, ObjetoEnum objeto, Long idObjeto);

    ComentarioDTO add(Authentication authentication, ComentarioView comentarioView, ObjetoEnum objetoEnum);
    ComentarioDTO update(Authentication authentication, ComentarioView comentarioView, Long idComentario);
    boolean  deleteById(Long idComentario);
    ComentarioDTO getComentarioById(Authentication authentication, Long idComentario);
    int getValoracionMedia(Long id, ObjetoEnum objeto);

}
