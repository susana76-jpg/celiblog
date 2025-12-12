package com.daw.celiblog.db.repository;

import com.daw.celiblog.db.entity.Post;
import com.daw.celiblog.db.entity.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT * FROM post WHERE estado = 'PENDIENTE'", nativeQuery = true)
    List<Post> getPostEstadoPendiente();

    @Query(value = "SELECT * FROM post WHERE estado =:estadoPublicacion", nativeQuery = true)
    List<Post> getByEstadoPublicacion(@Param("estadoPublicacion") String estadoPublicacion);

}
