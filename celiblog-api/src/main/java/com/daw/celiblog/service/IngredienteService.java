package com.daw.celiblog.service;

import com.daw.celiblog.db.entity.Receta;
import com.daw.celiblog.dto.IngredienteView;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IngredienteService {

    List<IngredienteView> add(Authentication authentication, List<IngredienteView> ingredienteView);
    IngredienteView update(Authentication authentication, IngredienteView ingredienteView, Long idIngrediente);
    boolean  deleteById(Long idReceta, Long idIngrediente);
    List<IngredienteView> updateAll(Authentication authentication, List<IngredienteView> ingredientes, Receta receta);

}
