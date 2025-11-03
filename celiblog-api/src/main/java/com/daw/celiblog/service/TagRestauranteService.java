package com.daw.celiblog.service;

import com.daw.celiblog.dto.TagRestauranteDTO;
import com.daw.celiblog.dto.TagRestauranteView;

import java.util.List;

public interface TagRestauranteService {
    List<String> obtenerTodosNombresTags();

    TagRestauranteDTO crearTagRestaurante(TagRestauranteView tagRestauranteView);

}
