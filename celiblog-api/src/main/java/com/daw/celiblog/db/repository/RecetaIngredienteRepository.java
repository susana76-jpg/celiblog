package com.daw.celiblog.db.repository;

import com.daw.celiblog.db.entity.RecetaIngrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecetaIngredienteRepository extends JpaRepository<RecetaIngrediente, Long> {
    @Query(value = "SELECT * FROM receta_ingrediente where id_receta =:idReceta and id_ingrediente = :idIngrediente", nativeQuery = true)
    Optional<RecetaIngrediente> getByIdRecetaAndIdIngrediente(@Param("idReceta") Long idReceta, @Param("idIngrediente") Long idIngrediente);

}
