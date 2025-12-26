package com.daw.celiblog.db.repository;

import com.daw.celiblog.db.entity.VistaReceta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VistaRecetaRepository extends JpaRepository<VistaReceta, Long> {

    @Query(value = """
            SELECT DISTINCT v.id_receta
            FROM vista_recetas v
            WHERE estado = 'APROBADO'
            AND(
              :keyword IS NULL
              OR UPPER(v.titulo)     LIKE CONCAT('%', UPPER(:keyword), '%')
              OR UPPER(v.subtitulo)  LIKE CONCAT('%', UPPER(:keyword), '%')
              OR UPPER(v.descripcion)LIKE CONCAT('%', UPPER(:keyword), '%')
              OR UPPER(v.nombre)     LIKE CONCAT('%', UPPER(:keyword), '%')
            )
            """, nativeQuery = true)
    List<Long> buscarVista(
            @Param("keyword") String keyword
    );

}
