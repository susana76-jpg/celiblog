package com.daw.celiblog.db.repository;

import com.daw.celiblog.db.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT * FROM post WHERE estado = 'PENDIENTE'", nativeQuery = true)
    List<Post> getPostEstadoPendiente();
    @Query(value = "SELECT * FROM post WHERE estado = 'APROBADO'", nativeQuery = true)
    List<Post> getPostEstadoAprobado();

    @Query(value = "SELECT * FROM post WHERE estado = 'RECHAZADO'", nativeQuery = true)
    List<Post> getPostEstadoRechazado();



}
