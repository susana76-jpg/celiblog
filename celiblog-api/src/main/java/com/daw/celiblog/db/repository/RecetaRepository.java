package com.daw.celiblog.db.repository;

import com.daw.celiblog.db.entity.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Long> {

    @Query(value = "SELECT * FROM receta", nativeQuery = true)
    List<Receta> findAll();

    @Query(value = """
    SELECT DISTINCT r.*
    FROM receta r
    JOIN tag_receta t ON r.id_receta = t.id_receta
    WHERE UPPER(t.nombre) LIKE %:nombreTag%
    """, nativeQuery = true)
    List<Receta> buscarRecetasPorNombreDeTag(@Param("nombreTag") String nombreTag);



}
