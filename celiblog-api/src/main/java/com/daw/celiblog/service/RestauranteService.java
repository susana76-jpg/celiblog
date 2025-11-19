package com.daw.celiblog.service;

import com.daw.celiblog.dto.RecetaDTO;
import com.daw.celiblog.dto.RestauranteDTO;
import com.daw.celiblog.dto.RestauranteView;
import com.daw.celiblog.dto.TagRestauranteDTO;
import com.daw.celiblog.enums.EstadoValidacion;

import java.util.List;

public interface RestauranteService {
    List<RestauranteDTO> obtenerTodos();
    RestauranteDTO obtenerPorId(Long id);
    RestauranteDTO crear(RestauranteDTO dto);
    RestauranteDTO actualizar(Long id, RestauranteDTO dto);
    boolean eliminar(Long id);

    List<TagRestauranteDTO> obtenerTagsRestaurantePorId(Long idRestaurante);
    List<RestauranteDTO> buscarRestaurantesPorNombreDeTag(String nombreTag);
    List<RestauranteDTO> buscarRestaurantesPorNombresDeTag(List<String> tags);
    RestauranteDTO crearRestaurante(RestauranteView restauranteView);
    RestauranteDTO update(RestauranteView restauranteView, Long idRestaurante);

    //gestión de aprobación de publicaciones de recetas
    List<RestauranteDTO> getRestaurantesEstadoPendiente();
    List<RestauranteDTO> getRestaurantesEstadoAprobado();
    List<RestauranteDTO> getRestaurantesEstadoRechazado();
    RestauranteDTO updateEstadoPublicacionRestaurante(Long idRestaurante, EstadoValidacion estado);


}
