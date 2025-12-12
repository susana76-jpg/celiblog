package com.daw.celiblog.db.repository;

import com.daw.celiblog.db.entity.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TagComentarioRepository extends JpaRepository<Restaurante, Long> {

    @Query(value = "SELECT * FROM tag_comentario", nativeQuery = true)
    List<Restaurante> findAll();

}
