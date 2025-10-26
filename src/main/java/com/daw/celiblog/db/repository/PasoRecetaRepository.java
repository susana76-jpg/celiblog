package com.daw.celiblog.db.repository;

import com.daw.celiblog.db.entity.PasoReceta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PasoRecetaRepository extends JpaRepository<PasoReceta, Long> {


    @Query(value = "SELECT * FROM paso_receta where id_receta = ? ORDER BY orden asc", nativeQuery = true)
    List<PasoReceta> findByIdReceta(Long idReceta);

    @Query(value = "SELECT * FROM paso_receta where id_receta = ? AND ORDEN = ?", nativeQuery = true)
    PasoReceta findPasoByIdRecetaAndOrden(Long idReceta, int orden);
}
