package com.daw.celiblog.db.repository;

import com.daw.celiblog.db.entity.Favorito;
import com.daw.celiblog.db.entity.Restaurante;
import com.daw.celiblog.enums.ObjetoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoritoRepository extends JpaRepository<Favorito, Long> {

    @Query(value = """
    SELECT DISTINCT id_referencia
    FROM favoritos
    WHERE id_usuario = :idUsuario
    AND tipo_referencia =:tipoReferencia
    """, nativeQuery = true)
    List<Long> getIdFavoritosByTipoReferencia(@Param("idUsuario") Long idUsuario, @Param("tipoReferencia") ObjetoEnum tipoReferencia);

    @Query(value = """
    SELECT DISTINCT *
    FROM favoritos
    WHERE id_usuario = :idUsuario
    AND tipo_referencia =:tipoReferencia
    """, nativeQuery = true)
    List<Favorito> getFavoritosByTipoReferencia(@Param("idUsuario") Long idUsuario, @Param("tipoReferencia") ObjetoEnum tipoReferencia);

}
