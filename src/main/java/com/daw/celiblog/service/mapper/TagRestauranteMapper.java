package com.daw.celiblog.service.mapper;

import com.daw.celiblog.db.entity.TagRestaurante;
import com.daw.celiblog.db.repository.RestauranteRepository;
import com.daw.celiblog.dto.TagRestauranteDTO;

import java.util.List;
import java.util.stream.Collectors;


public class TagRestauranteMapper {
    private final RestauranteRepository restauranteRepository;
    private TagRestauranteDTO dto;
    private TagRestaurante entity;
    public TagRestauranteMapper(TagRestaurante entity, TagRestauranteDTO dto, RestauranteRepository restauranteRepository){
        this.entity = entity;
        this.dto = dto;
        this.restauranteRepository = restauranteRepository;
    }

    public static TagRestauranteDTO entityToDto(TagRestaurante source){
        return new TagRestauranteDTO(source.getIdTag(), source.getNombre(), source.getRestaurante().getIdRestaurante());
    }

    public static TagRestaurante dtoToEntityWithRestaurante(TagRestauranteDTO source){
        return new TagRestaurante(source.getIdTag(), source.getNombre(), RestauranteMapper.dtoToEntity(source.getRestaurante()));
    }

    public static TagRestauranteDTO entityToDtoWithRestaurante(TagRestaurante source){
        return new TagRestauranteDTO(source.getIdTag(), source.getNombre(), RestauranteMapper.entityToDto(source.getRestaurante()));
    }

    public static TagRestaurante dtoToEntity(TagRestauranteDTO source){
        return new TagRestaurante(source.getIdTag(), source.getNombre(),  RestauranteMapper.dtoToEntity(source.getRestaurante()));
           }

    public static List<TagRestaurante> dtoToEntityList(List<TagRestauranteDTO> source){
        return source.stream().map(s -> {
            return dtoToEntity(s);
        }).collect(Collectors.toList());
    }

    public static List<TagRestauranteDTO> entityToDtoList(List<TagRestaurante> source){
        return source.stream().map(s -> {
            return entityToDto(s);
        }).collect(Collectors.toList());
    }

}
