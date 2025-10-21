package com.daw.celiblog.service.mapper;

import com.daw.celiblog.db.entity.TipoComentario;
import com.daw.celiblog.dto.TipoComentarioDTO;

import java.util.List;
import java.util.stream.Collectors;


public class TipoComentarioMapper {
    private TipoComentarioDTO dto;
    private TipoComentario entity;
    public TipoComentarioMapper(TipoComentario entity, TipoComentarioDTO dto){
        this.entity = entity;
        this.dto = dto;
    }

    public static TipoComentarioDTO entityToDto(TipoComentario source){
        return new TipoComentarioDTO(source.getIdTipoComentario(), source.getNombre(), source.getTema());
    }

    public static TipoComentario dtoToEntity(TipoComentarioDTO source){
        return new TipoComentario(source.getIdTipoComentario(), source.getNombre(), source.getTema());
    }

    public static List<TipoComentario> dtoToEntityList(List<TipoComentarioDTO> source){
        return source.stream().map(s -> {
            return dtoToEntity(s);
        }).collect(Collectors.toList());
    }

    public static List<TipoComentarioDTO> entityToDtoList(List<TipoComentario> source){
        return source.stream().map(s -> {
            return entityToDto(s);
        }).collect(Collectors.toList());
    }

}
