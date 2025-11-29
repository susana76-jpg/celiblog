package com.daw.celiblog.service;

import com.daw.celiblog.dto.*;
import com.daw.celiblog.enums.EstadoValidacionEnum;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface RestauranteService {
    List<RestauranteDTO> obtenerTodos();
    RestauranteDTO obtenerPorId(Long id);
    RestauranteDTO crear(RestauranteDTO dto);
    RestauranteDTO actualizar(Long id, RestauranteDTO dto) throws JsonProcessingException;
    List<RestauranteDTO> byUbicacion(String ubicacion);

    void actualizarGeolocalizacion() throws JsonProcessingException;
    boolean eliminar(Long id);

    List<TagRestauranteDTO> obtenerTagsRestaurantePorId(Long idRestaurante);
    List<RestauranteDTO> buscarRestaurantesPorNombreDeTag(String nombreTag);
    List<RestauranteDTO> buscarRestaurantesPorNombresDeTag(List<String> tags);
    RestauranteDTO crearRestaurante(RestauranteView restauranteView) throws JsonProcessingException;
    RestauranteDTO update(RestauranteView restauranteView, Long idRestaurante) throws JsonProcessingException;

    //gestión de aprobación de publicaciones de recetas
    List<RestauranteDTO> getRestaurantesEstadoPendiente();
    List<RestauranteDTO> getRestaurantesEstadoAprobado();
    List<RestauranteDTO> getRestaurantesEstadoRechazado();
    RestauranteDTO updateEstadoPublicacionRestaurante(Long idRestaurante, EstadoValidacionEnum estado);

    //usuario logado
    List<RestauranteDTO> obtenerTodosConFavoritosUsuario(String emailUsuario);
    List<RestauranteDTO> obtenerRestaurantesFavoritosUsuario(String emailUsuario);


}
