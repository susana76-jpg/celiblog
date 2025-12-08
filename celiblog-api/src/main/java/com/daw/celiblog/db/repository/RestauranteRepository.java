package com.daw.celiblog.db.repository;

import com.daw.celiblog.db.entity.Receta;
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

    @Query(value = """
    SELECT DISTINCT r.*
    FROM restaurante r
    JOIN tag_restaurante t ON r.id_restaurante = t.id_restaurante
    WHERE UPPER(t.nombre) LIKE %:nombreTag%
    """, nativeQuery = true)
    List<Restaurante> buscarRestaurantesPorNombreDeTag(@Param("nombreTag") String nombreTag);

    @Query(value = "SELECT * FROM restaurante WHERE estado = 'PENDIENTE'", nativeQuery = true)
    List<Restaurante> getRestaurantesEstadoPendiente();

    @Query(value = """
    SELECT DISTINCT *
    FROM restaurante 
    WHERE UPPER(ubicacion) LIKE %:ubicacion%
    """, nativeQuery = true)
    List<Restaurante> findByUbicacion(@Param("ubicacion") String ubicacion);

    @Query(value = "SELECT * FROM restaurante WHERE estado =:estadoPublicacion", nativeQuery = true)
    List<Restaurante> getByEstadoPublicacion(@Param("estadoPublicacion") String estadoPublicacion);

}