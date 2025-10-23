package com.daw.celiblog.service.mapper;

import com.daw.celiblog.db.entity.TagReceta;
import com.daw.celiblog.dto.TagRecetaDTO;
import com.daw.celiblog.service.RecetaService;

import java.util.List;
import java.util.stream.Collectors;


public class TagRecetaMapper {
    private final TagRecetaDTO dto;
    private final TagReceta entity;
    private RecetaMapper recetaMapper;
    private final RecetaService recetaService;

    public TagRecetaMapper(TagReceta entity, TagRecetaDTO dto, RecetaMapper recetaMapper, RecetaService recetaService){
        this.entity = entity;
        this.dto = dto;
        this.recetaMapper = recetaMapper;
        this.recetaService = recetaService;
    }

    public static TagRecetaDTO entityToDto(TagReceta source){
        return new TagRecetaDTO(source.getIdTag(), source.getNombre(), source.getReceta().getIdReceta());
    }

    public static TagReceta dtoToEntity(TagRecetaDTO source){
        return new TagReceta(source.getIdTag(), source.getNombre(), RecetaMapper.dtoToEntity(source.getRecetaDTO()));
    }

    public static List<TagReceta> dtoToEntityList(List<TagRecetaDTO> source){
        return source.stream().map(s -> {
            return dtoToEntity(s);
        }).collect(Collectors.toList());
    }

    public static List<TagRecetaDTO> entityToDtoList(List<TagReceta> source){
        return source.stream().map(s -> {
            return entityToDto(s);
        }).collect(Collectors.toList());
    }

}
