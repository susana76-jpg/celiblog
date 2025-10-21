package com.daw.celiblog.db.repository;

import com.daw.celiblog.db.entity.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagComentarioRepository extends JpaRepository<Restaurante, Long> {

    @Query(value = "SELECT * FROM TAG_COMENTARIO", nativeQuery = true)
    List<Restaurante> findAll();

}
