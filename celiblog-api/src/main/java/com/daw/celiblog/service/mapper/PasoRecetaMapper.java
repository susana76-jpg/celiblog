package com.daw.celiblog.service.mapper;

import com.daw.celiblog.db.entity.PasoReceta;
import com.daw.celiblog.dto.PasoRecetaDTO;

import java.util.List;
import java.util.stream.Collectors;


public class PasoRecetaMapper {
    private PasoReceta entity;
    private PasoRecetaDTO dto;
    public PasoRecetaMapper(PasoReceta entity, PasoRecetaDTO dto){
        this.entity = entity;
        this.dto = dto;
    }

    public static PasoRecetaDTO entityToDto(PasoReceta source){
        return new PasoRecetaDTO(source.getIdPaso(), source.getDescripcion(), source.getOrden(), source.getReceta().getIdReceta());
    }
    public static PasoRecetaDTO entityToDtoWithReceta(PasoReceta source){
        return new PasoRecetaDTO(source.getIdPaso(), source.getDescripcion(), source.getOrden(), RecetaMapper.entityToDto(source.getReceta()) ,source.getReceta().getIdReceta());
    }

    public static PasoReceta dtoToEntity(PasoRecetaDTO source){
        return new PasoReceta(source.getIdPaso(), source.getDescripcion(), source.getOrden(), RecetaMapper.dtoToEntity(source.getReceta()));
    }

    public static List<PasoReceta> dtoToEntityList(List<PasoRecetaDTO> source){
        return source.stream().map(s -> {
            return dtoToEntity(s);
        }).collect(Collectors.toList());
    }

    public static List<PasoRecetaDTO> entityToDtoList(List<PasoReceta> source){
        return source.stream().map(s -> {
            return entityToDto(s);
        }).collect(Collectors.toList());
    }

}
