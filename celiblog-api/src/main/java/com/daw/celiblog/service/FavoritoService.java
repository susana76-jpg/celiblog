package com.daw.celiblog.service;

import com.daw.celiblog.db.entity.Favorito;
import com.daw.celiblog.dto.FavoritoDTO;
import com.daw.celiblog.dto.FavoritoView;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface FavoritoService {

    FavoritoDTO addFavorito(FavoritoView favoritoView);
    boolean deleteFavorito(Long idFavorito);
}
