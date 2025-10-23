package com.daw.celiblog.service.mapper;

import com.daw.celiblog.db.entity.ComentarioRestaurante;
import com.daw.celiblog.dto.ComentarioRestauranteDTO;

import java.util.List;
import java.util.stream.Collectors;


public class ComentarioRestauranteMapper {
    private ComentarioRestauranteDTO dto;
    private ComentarioRestaurante entity;
    public ComentarioRestauranteMapper(ComentarioRestaurante entity, ComentarioRestauranteDTO dto){
        this.entity = entity;
        this.dto = dto;
    }

    public static ComentarioRestauranteDTO entityToDto(ComentarioRestaurante source){
        return new ComentarioRestauranteDTO(source.getIdComentarioRestaurante(), source.getValoracion(),ComentarioMapper.entityToDto(source.getComentario()) ,source.getRestaurante().getIdRestaurante() ,source.getFecha());
    }

    public static ComentarioRestaurante dtoToEntity(ComentarioRestauranteDTO source){
        return new ComentarioRestaurante(source.getIdComentarioRestaurante(), source.getValoracion(),ComentarioMapper.dtoToEntity(source.getComentario()) ,RestauranteMapper.dtoToEntity(source.getRestaurante()) ,source.getFecha());
    }

    public static List<ComentarioRestaurante> dtoToEntityList(List<ComentarioRestauranteDTO> source){
        return source.stream().map(s -> {
            return dtoToEntity(s);
        }).collect(Collectors.toList());
    }

    public static List<ComentarioRestauranteDTO> entityToDtoList(List<ComentarioRestaurante> source){
        return source.stream().map(s -> {
            return entityToDto(s);
        }).collect(Collectors.toList());
    }

}
