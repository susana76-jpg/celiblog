package com.daw.celiblog.service.impl;

import com.daw.celiblog.db.entity.Restaurante;
import com.daw.celiblog.db.repository.RestauranteRepository;
import com.daw.celiblog.db.repository.TagRestauranteRepository;
import com.daw.celiblog.dto.RestauranteDTO;
import com.daw.celiblog.dto.TagRestauranteDTO;
import com.daw.celiblog.service.RestauranteService;
import com.daw.celiblog.service.mapper.RestauranteMapper;
import com.daw.celiblog.service.mapper.TagRestauranteMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestauranteServiceImpl implements RestauranteService {

    private final RestauranteRepository restauranteRepository;

    private final TagRestauranteRepository tagRestauranteRepository;


    public RestauranteServiceImpl(RestauranteRepository restauranteRepository, TagRestauranteRepository tagRestauranteRepository) {
        this.restauranteRepository = restauranteRepository;
        this.tagRestauranteRepository = tagRestauranteRepository;
    }


    @Override
    public List<RestauranteDTO> obtenerTodos() {
        return restauranteRepository.findAll().stream()
                .map(RestauranteMapper::entityToDto)
                .collect(Collectors.toList());
   }

    @Override
    public RestauranteDTO obtenerPorId(Long id) {
        return restauranteRepository
                .findById(id)
                .map(RestauranteMapper::entityToDto)
                .orElse(null);
    }

    @Override
    public RestauranteDTO crear(RestauranteDTO dto) {
        return RestauranteMapper.entityToDto(this.restauranteRepository.save(RestauranteMapper.dtoToEntity(dto)));
    }

    @Override
    public RestauranteDTO actualizar(Long id, RestauranteDTO dto) {
        Optional<Restaurante> rest = this.restauranteRepository.findById(id);
        if(rest.isPresent()){
            Restaurante restaurante = rest.get();
            restaurante.setDireccion(dto.getDireccion());
            restaurante.setDescripcion(dto.getDescripcion());
            restaurante.setUbicacion(dto.getUbicacion());
            restaurante.setImagenUrl(dto.getImagenUrl());
            restaurante.setNombre(dto.getNombre());
            restaurante.setUrlWeb(dto.getUrlWeb());
            return RestauranteMapper.entityToDto(this.restauranteRepository.save(restaurante));
        }
        return null;

    }

    @Override
    public void eliminar(Long id) {
        if(this.restauranteRepository.findById(id).isPresent()){
            restauranteRepository.deleteById(id);
        }
    }

    @Override
    public List<TagRestauranteDTO> obtenerTagsRestaurantePorId(Long idRestaurante) {
        return TagRestauranteMapper.entityToDtoList(tagRestauranteRepository.findByIdRestaurante(idRestaurante));

    }


    @Override
    public List<RestauranteDTO> buscarRestaurantesPorNombreDeTag(String nombreTag) {
        return RestauranteMapper.entityToDtoList(this.restauranteRepository.buscarRestaurantesPorNombreDeTag(nombreTag.toUpperCase()));
    }

    @Override
    public List<RestauranteDTO> buscarRestaurantesPorNombresDeTag(List<String> tags) {
        Set<RestauranteDTO> restaurantes = new HashSet<>();
       for(String tag:tags){
           restaurantes.addAll(new HashSet<>(this.buscarRestaurantesPorNombreDeTag(tag)));
       }
       return restaurantes.stream().toList();
    }


}
