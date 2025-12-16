package com.daw.celiblog.service;

import com.daw.celiblog.dto.EstadisticaDTO;
import com.daw.celiblog.dto.FavoritoDTO;
import com.daw.celiblog.enums.ObjetoEnum;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface FavoritoService {

    FavoritoDTO addFavorito(Authentication authentication, Long idReferencia, ObjetoEnum objetoEnum);
    boolean deleteFavorito(Long idFavorito);

    List<?> getFavoritosByReferencia(Authentication authentication, ObjetoEnum objetoEnum);
    EstadisticaDTO getFavoritosEstadistica(Authentication authentication);
}
