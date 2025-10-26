package com.daw.celiblog.db.repository;

import com.daw.celiblog.db.entity.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoritoRepository extends JpaRepository<Favorito, Long> {

    //favoritos restaurantes
    @Query(value = "SELECT * FROM favoritos WHERE id_usuario = ? AND tipo_referencia = 'restaurante'", nativeQuery = true)
    List<Favorito> getFavoritosRestaurantesByIdUsuario(Long idUsuario);

    @Query(value = "SELECT id_referencia FROM favoritos WHERE id_usuario = ? AND tipo_referencia = 'restaurante'", nativeQuery = true)
    List<Long> getFavoritosIdRestaurantesByIdUsuario(Long idUsuario);

    //favoritos recetas
    @Query(value = "SELECT * FROM favoritos WHERE id_usuario = ? AND tipo_referencia = 'receta'", nativeQuery = true)
    List<Favorito> getFavoritosRecetasByIdUsuario(Long idUsuario);

    @Query(value = "SELECT id_referencia FROM favoritos WHERE id_usuario = ? AND tipo_referencia = 'receta'", nativeQuery = true)
    List<Long> getFavoritosIdRecetasByIdUsuario(Long idUsuario);

    //favoritos post
    @Query(value = "SELECT * FROM favoritos WHERE id_usuario = ? AND tipo_referencia = 'post'", nativeQuery = true)
    List<Favorito> getFavoritosPostByIdUsuario(Long idUsuario);

    @Query(value = "SELECT id_referencia FROM favoritos WHERE id_usuario = ? AND tipo_referencia = 'post'", nativeQuery = true)
    List<Long> getFavoritosIdPostByIdUsuario(Long idUsuario);
}
