package com.daw.celiblog.db.repository;

import com.daw.celiblog.db.entity.Comentario;
import com.daw.celiblog.db.entity.ComentarioRestaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComentarioRestauranteRepository extends JpaRepository<ComentarioRestaurante, Long> {


    @Query(value = "SELECT * FROM comentario_restaurante WHERE id_restaurante = ? ORDER BY fecha", nativeQuery = true)
    List<ComentarioRestaurante> getComentariosByIdRestaurante(Long idRestaurante);
}
