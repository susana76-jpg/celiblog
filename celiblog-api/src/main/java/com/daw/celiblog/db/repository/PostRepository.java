package com.daw.celiblog.db.repository;

import com.daw.celiblog.db.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT * FROM post WHERE estado = 'PENDIENTE'", nativeQuery = true)
    List<Post> getPostEstadoPendiente();

    @Query(value = "SELECT count(*) FROM post", nativeQuery = true)
    int countAll();

    @Query(value = "SELECT * FROM post WHERE estado =:estadoPublicacion", nativeQuery = true)
    List<Post> getByEstadoPublicacion(@Param("estadoPublicacion") String estadoPublicacion);

    @Query(value = """
            SELECT DISTINCT id_post
            FROM post
            WHERE (:keyword IS NULL OR UPPER(titulo) LIKE CONCAT('%', UPPER(:keyword), '%'))
            OR (:keyword IS NULL OR UPPER(subtitulo) LIKE CONCAT('%', UPPER(:keyword), '%'))
            OR (:keyword IS NULL OR UPPER(contenido) LIKE CONCAT('%', UPPER(:keyword), '%'))
            AND estado = 'APROBADO'
            """, nativeQuery = true)
    List<Long> buscar(
            @Param("keyword") String keyword
    );

    @Query(value = """
    SELECT DISTINCT p.*
    FROM post p
    JOIN tag_post t ON p.id_post = p.id_post
    WHERE UPPER(t.nombre) LIKE %:nombreTag%
    AND p.estado = 'APROBADO'
    """, nativeQuery = true)
    List<Post> buscarPostsPorNombreDeTag(@Param("nombreTag") String nombreTag);

    @Query(value = "SELECT * FROM post WHERE id_usuario =:idUsuario", nativeQuery = true)
    List<Post> getByIdUsuario(@Param("idUsuario")Long idUsuario);

}
