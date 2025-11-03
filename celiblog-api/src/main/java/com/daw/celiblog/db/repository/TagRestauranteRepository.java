package com.daw.celiblog.db.repository;

import com.daw.celiblog.db.entity.TagRestaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRestauranteRepository extends JpaRepository<TagRestaurante, Long> {

    @Query(value = "SELECT * FROM tag_restaurante", nativeQuery = true)
    List<TagRestaurante> findAll();

    @Query(value = "SELECT * FROM tag_restaurante WHERE id_Restaurante = 1", nativeQuery = true)
    List<TagRestaurante> findByIdRestaurante(Long idRestaurante);

    @Query(value = "SELECT DISTINCT nombre FROM tag_restaurante ORDER BY nombre ASC", nativeQuery = true)
    List<String> findAllTagsOrder();

}
