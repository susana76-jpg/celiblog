package com.daw.celiblog.db.repository;

import com.daw.celiblog.db.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {


    //public Comentario getComentariosById(Long idComentario);
}
