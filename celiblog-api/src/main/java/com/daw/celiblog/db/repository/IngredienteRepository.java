package com.daw.celiblog.db.repository;

import com.daw.celiblog.db.entity.Ingrediente;
import com.daw.celiblog.db.entity.PasoReceta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {


    @Query(value = """
    SELECT i.id_ingrediente FROM ingrediente i
    JOIN receta_ingrediente ri
    ON ri.id_ingrediente = i.id_ingrediente
    WHERE ri.id_receta = :idReceta;
    """, nativeQuery = true)
    List<Long> getByIdReceta(@Param("idReceta") Long idReceta);


}
