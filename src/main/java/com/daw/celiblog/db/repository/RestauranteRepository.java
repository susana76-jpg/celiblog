package com.daw.celiblog.db.repository;

import com.daw.celiblog.db.entity.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    @Query(value = "SELECT * FROM restaurante", nativeQuery = true)
    List<Restaurante> findAll();
}
