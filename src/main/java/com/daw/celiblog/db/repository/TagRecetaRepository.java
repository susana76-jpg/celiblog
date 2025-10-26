package com.daw.celiblog.db.repository;

import com.daw.celiblog.db.entity.TagReceta;
import com.daw.celiblog.db.entity.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRecetaRepository extends JpaRepository<TagReceta, Long> {

    @Query(value = "SELECT * FROM TAG_RECETA", nativeQuery = true)
    List<TagReceta> findAll();

    @Query(value = "SELECT * FROM TAG_RECETA WHERE ID_RECETA = ?", nativeQuery = true)
    List<TagReceta> getTagsByIdReceta(Long idReceta);

    @Query(value = "SELECT DISTINCT nombre FROM TAG_RECETA ORDER BY nombre ASC", nativeQuery = true)
    List<String> findAllTagsOrder();

    @Query(value = """
    SELECT t.*
    FROM TAG_RECETA t
    WHERE ID_RECETA = ?
    AND UPPER(t.nombre) LIKE %?%
    """, nativeQuery = true)
    TagReceta buscarTagByIdRecetaAndNombreTag(Long idReceta, String nombreTag);


}
