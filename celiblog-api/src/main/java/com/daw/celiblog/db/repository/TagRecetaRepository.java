package com.daw.celiblog.db.repository;

import com.daw.celiblog.db.entity.TagReceta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TagRecetaRepository extends JpaRepository<TagReceta, Long> {

    @Query(value = "SELECT * FROM tag_receta", nativeQuery = true)
    List<TagReceta> findAll();

    @Query(value = "SELECT DISTINCT nombre FROM tag_receta ORDER BY nombre ASC", nativeQuery = true)
    List<String> findAllTagsOrder();

    @Query(value = """
    SELECT t.*
    FROM tag_receta t
    WHERE id_receta = ?
    AND UPPER(t.nombre) LIKE %?%
    """, nativeQuery = true)
    TagReceta buscarTagByIdRecetaAndNombreTag(Long idReceta, String nombreTag);


}
