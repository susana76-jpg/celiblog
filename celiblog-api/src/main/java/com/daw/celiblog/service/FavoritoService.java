package com.daw.celiblog.service;

import com.daw.celiblog.db.entity.Favorito;
import com.daw.celiblog.dto.FavoritoDTO;
import com.daw.celiblog.dto.FavoritoView;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoritoService {
    //favoritos restaurantes
    List<FavoritoDTO> getFavoritosRestaurantesByIdUsuario(Long idUsuario);
    List<Long> getFavoritosIdRestaurantesByIdUsuario(Long idUsuario);
    //favoritos recetas
    List<FavoritoDTO> getFavoritosRecetasByIdUsuario(Long idUsuario);
    List<Long> getFavoritosIdRecetasByIdUsuario(Long idUsuario);
    //favoritos post
    List<FavoritoDTO> getFavoritosPostByIdUsuario(Long idUsuario);
    List<Long> getFavoritosIdPostByIdUsuario(Long idUsuario);
    FavoritoDTO addFavorito(FavoritoView favoritoView);
    boolean deleteFavorito(Long idFavorito);
}
