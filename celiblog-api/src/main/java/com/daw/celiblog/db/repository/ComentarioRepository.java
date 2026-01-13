package com.daw.celiblog.db.repository;

import com.daw.celiblog.db.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    @Query(value = """
    SELECT DISTINCT *
    FROM comentario
    WHERE id_objeto_comentado = :idObjetoComentado
    AND objeto_comentado =:objetoComentado
    AND estado = "APROBADO"
    ORDER BY fecha_publicacion ASC
    """, nativeQuery = true)
    List<Comentario> allComentariosByObject(@Param("objetoComentado")String objetoComentado, @Param("idObjetoComentado")Long idObjetoComentado);

    @Query(value = "SELECT * FROM comentario WHERE estado =:estadoPublicacion", nativeQuery = true)
    List<Comentario> getByEstadoPublicacion(@Param("estadoPublicacion") String estadoPublicacion);

    @Query(value = "SELECT count(*) FROM comentario", nativeQuery = true)
    int countAll();

    @Query(value = "SELECT * FROM comentario WHERE id_usuario =:idUsuario", nativeQuery = true)
    List<Comentario> getByIdUsuario(@Param("idUsuario")Long idUsuario);

    @Query(value = """
            SELECT COALESCE(AVG(valoracion),0)
            FROM comentario
            WHERE id_objeto_comentado = :idObjeto
            AND objeto_comentado =:objetoComentado
            AND estado = 'APROBADO'
            """, nativeQuery = true)
    int getValoracionObjeto(@Param("idObjeto")Long idObjeto, @Param("objetoComentado")String objetoComentado);

    @Query(value= """
            SELECT COUNT(*)
            FROM comentario 
            WHERE id_usuario =:idUsuario
            """, nativeQuery = true)
    int getNumComentariosByUsuario(@Param("idUsuario")Long idUsuario);



}
