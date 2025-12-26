package com.daw.celiblog.db.repository;

import com.daw.celiblog.db.entity.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
    @Query(value = "SELECT * FROM ingrediente where id_receta = ? ", nativeQuery = true)
    List<Ingrediente> findByIdReceta(Long idReceta);
}
