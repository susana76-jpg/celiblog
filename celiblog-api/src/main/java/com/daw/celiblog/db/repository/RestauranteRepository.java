package com.daw.celiblog.db.repository;

import com.daw.celiblog.db.entity.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    @Query(value = "SELECT * FROM restaurante", nativeQuery = true)
    List<Restaurante> findAll();

    @Query(value = "SELECT count(*) FROM restaurante", nativeQuery = true)
    int countAll();

    @Query(value = """
    SELECT DISTINCT r.*
    FROM restaurante r
    JOIN tag_restaurante t ON r.id_restaurante = t.id_restaurante
    WHERE UPPER(t.nombre) LIKE %:nombreTag%
    AND r.estado = 'APROBADO'
    """, nativeQuery = true)
    List<Restaurante> buscarRestaurantesPorNombreDeTag(@Param("nombreTag") String nombreTag);

    @Query(value = """
    SELECT DISTINCT id_restaurante
    FROM restaurante 
    WHERE (:ubicacion IS NULL OR UPPER(ubicacion) LIKE CONCAT('%', UPPER(:ubicacion), '%'))
    AND estado = 'APROBADO'
    """, nativeQuery = true)
    List<Long> findByUbicacion(@Param("ubicacion") String ubicacion);

    @Query(value = "SELECT * FROM restaurante WHERE estado =:estadoPublicacion", nativeQuery = true)
    List<Restaurante> getByEstadoPublicacion(@Param("estadoPublicacion") String estadoPublicacion);

    @Query(value = "SELECT * FROM restaurante WHERE id_usuario =:idUsuario AND estado = 'APROBADO'", nativeQuery = true)
    List<Restaurante> getByIdUsuario(@Param("idUsuario")Long idUsuario);

}