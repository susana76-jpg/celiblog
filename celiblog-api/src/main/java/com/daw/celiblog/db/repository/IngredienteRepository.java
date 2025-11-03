package com.daw.celiblog.db.repository;

import com.daw.celiblog.db.entity.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
}
