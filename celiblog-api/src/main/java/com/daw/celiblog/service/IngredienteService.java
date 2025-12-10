package com.daw.celiblog.service;

import com.daw.celiblog.dto.IngredienteDTO;
import com.daw.celiblog.dto.IngredienteView;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IngredienteService {

    List<IngredienteView> add(Authentication authentication, List<IngredienteView> ingredienteView);
    IngredienteView update(Authentication authentication, IngredienteView ingredienteView, Long idIngrediente);
    boolean  deleteById(Long idReceta, Long idIngrediente);

}
