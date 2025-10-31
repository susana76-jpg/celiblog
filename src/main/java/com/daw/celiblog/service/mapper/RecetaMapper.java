package com.daw.celiblog.service.mapper;

import com.daw.celiblog.db.entity.Receta;
import com.daw.celiblog.dto.RecetaDTO;
import com.daw.celiblog.dto.UsuarioDTO;
import com.daw.celiblog.util.EstadoValidacion;

import java.util.Date;
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
        return new RecetaDTO(source.getIdReceta(),
                UsuarioMapper.entityToDto(source.getUsuario()),
                source.getDescripcion(),
                source.getFechaCreacion(),
                source.getImagenUrl(),
                source.getDificultad(),
                source.getTitulo(),
                source.getEstado(),
                source.getFechaValidacion());
    }
    public static Receta dtoToEntity(RecetaDTO source){
        return new Receta(source.getIdReceta(),
                UsuarioMapper.dtoToEntity(source.getUsuario()),
                source.getDescripcion(),
                source.getFechaCreacion(),
                source.getImagenUrl(),
                source.getDificultad(),
                source.getTitulo(),
                source.getEstado(),
                source.getFechaValidacion());
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
