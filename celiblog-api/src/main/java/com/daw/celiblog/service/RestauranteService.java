package com.daw.celiblog.service;

import com.daw.celiblog.dto.*;
import com.daw.celiblog.enums.EstadoValidacionEnum;
import com.daw.celiblog.enums.TipoRestauranteEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface RestauranteService {
    List<RestauranteDTO> all(Authentication authentication);
    RestauranteDTO getById(Authentication authentication, Long id);
    RestauranteDTO add(Authentication authentication, RestauranteView restauranteView) throws JsonProcessingException;
    RestauranteDTO update(Long id, RestauranteView restauranteView) throws JsonProcessingException;
    List<RestauranteDTO> byUbicacionAndTipo(Authentication authentication, String ubicacion, List<TipoRestauranteEnum> tiposRestaurante);

    void actualizarGeolocalizacion() throws JsonProcessingException;
    boolean deleteById(Long id);

    List<TagRestauranteDTO> obtenerTagsRestaurantePorId(Long idRestaurante);
    List<RestauranteDTO> buscarRestaurantesPorNombreDeTag(String nombreTag);
    List<RestauranteDTO> buscarRestaurantesPorNombresDeTag(List<String> tags);
    RestauranteDTO crearRestaurante(RestauranteView restauranteView) throws JsonProcessingException;
    RestauranteDTO update(RestauranteView restauranteView, Long idRestaurante) throws JsonProcessingException;




}
