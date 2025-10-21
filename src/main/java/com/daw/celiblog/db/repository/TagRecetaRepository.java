package com.daw.celiblog.db.repository;

import com.daw.celiblog.db.entity.TagReceta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRecetaRepository extends JpaRepository<TagReceta, Long> {

    @Query(value = "SELECT * FROM TAG_RECETA", nativeQuery = true)
    List<TagReceta> findAll();

    @Query(value = "SELECT * FROM TAG_RECETA WHERE ID_RECETA = ?", nativeQuery = true)
    List<TagReceta> getTagsByIdReceta(Long idReceta);

}
