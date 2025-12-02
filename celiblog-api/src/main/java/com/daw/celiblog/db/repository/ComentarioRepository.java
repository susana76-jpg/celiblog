package com.daw.celiblog.db.repository;

import com.daw.celiblog.db.entity.Comentario;
import com.daw.celiblog.dto.ComentarioDTO;
import com.daw.celiblog.dto.ComentarioView;
import com.daw.celiblog.enums.ObjetoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    @Query(value = """
    SELECT DISTINCT *
    FROM comentario
    WHERE id_objeto_comentado = :idObjetoComentado
    AND objeto_comentado =:objetoComentado
    ORDER BY fecha_publicacion ASC
    """, nativeQuery = true)
    List<Comentario> allComentariosByObject(@Param("objetoComentado")String objetoComentado, @Param("idObjetoComentado")Long idObjetoComentado);



}
