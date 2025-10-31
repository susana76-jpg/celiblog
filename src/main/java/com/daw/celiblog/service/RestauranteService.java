package com.daw.celiblog.service;

import com.daw.celiblog.dto.RestauranteDTO;
import com.daw.celiblog.dto.TagRestauranteDTO;

import java.util.List;

public interface RestauranteService {
    List<RestauranteDTO> obtenerTodos();
    RestauranteDTO obtenerPorId(Long id);
    RestauranteDTO crear(RestauranteDTO dto);
    RestauranteDTO actualizar(Long id, RestauranteDTO dto);
    void eliminar(Long id);

    List<TagRestauranteDTO> obtenerTagsRestaurantePorId(Long idRestaurante);
    List<RestauranteDTO> buscarRestaurantesPorNombreDeTag(String nombreTag);

    List<RestauranteDTO> buscarRestaurantesPorNombresDeTag(List<String> tags);
}
