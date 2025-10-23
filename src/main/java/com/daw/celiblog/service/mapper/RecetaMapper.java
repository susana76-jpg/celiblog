package com.daw.celiblog.service.mapper;

import com.daw.celiblog.db.entity.Receta;
import com.daw.celiblog.dto.RecetaDTO;

import java.util.List;
import java.util.stream.Collectors;


public class RecetaMapper {
    private Receta entity;
    private RecetaDTO dto;

    public RecetaMapper(Receta entity, RecetaDTO dto){
        this.entity = entity;
        this.dto = dto;
    }

    public static RecetaDTO entityToDto(Receta source){
        return new RecetaDTO(source.getIdReceta(), source.getDescripcion(), source.getFechaCreacion(), source.getImagenUrl(), source.getTitulo(), source.getDificultad());
    }

    public static Receta dtoToEntity(RecetaDTO source){
        return new Receta(source.getIdReceta(), source.getDescripcion(), source.getFechaCreacion(), source.getImagenUrl(), source.getTitulo(), source.getDificultad());
    }

    public static List<Receta> dtoToEntityList(List<RecetaDTO> source){
        return source.stream().map(s -> {
            return dtoToEntity(s);
        }).collect(Collectors.toList());
    }

    public static List<RecetaDTO> entityToDtoList(List<Receta> source){
        return source.stream().map(s -> {
            return entityToDto(s);
        }).collect(Collectors.toList());
    }

}
