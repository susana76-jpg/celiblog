package com.daw.celiblog.db.repository;

import com.daw.celiblog.db.entity.TagPost;
import com.daw.celiblog.db.entity.TagReceta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagPostRepository extends JpaRepository<TagPost, Long> {

    @Query(value = "SELECT * FROM tag_post", nativeQuery = true)
    List<TagPost> findAll();

    @Query(value = "SELECT DISTINCT nombre FROM tag_post ORDER BY nombre ASC", nativeQuery = true)
    List<String> findAllTagsOrder();

    @Query(value = """
    SELECT t.*
    FROM tag_post t
    WHERE id_post = ?
    AND UPPER(t.nombre) LIKE %?%
    """, nativeQuery = true)
    TagPost buscarTagByIdPostAndNombreTag(Long idPost, String nombreTag);


}
