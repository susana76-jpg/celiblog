package com.daw.celiblog.service.mapper;

import com.daw.celiblog.db.entity.Comentario;
import com.daw.celiblog.dto.ComentarioDTO;

import java.util.List;
import java.util.stream.Collectors;


public class ComentarioMapper {
    private ComentarioDTO dto;
    private Comentario entity;
    public ComentarioMapper(Comentario entity, ComentarioDTO dto){
        this.entity = entity;
        this.dto = dto;
    }

    public static ComentarioDTO entityToDto(Comentario source){
        return new ComentarioDTO(source.getIdComentario(), source.getComentarioUrl(), source.getContenido(), UsuarioMapper.entityToDto(source.getUsuario()) ,TipoComentarioMapper.entityToDto(source.getTipoComentario()) );
    }

    public static Comentario dtoToEntity(ComentarioDTO source){
        return new Comentario(source.getIdComentario(), source.getComentarioUrl(), source.getContenido(), UsuarioMapper.dtoToEntity(source.getUsuario()) ,TipoComentarioMapper.dtoToEntity(source.getTipoComentario()) );
    }


    public static List<Comentario> dtoToEntityList(List<ComentarioDTO> source){
        return source.stream().map(s -> {
            return dtoToEntity(s);
        }).collect(Collectors.toList());
    }

    public static List<ComentarioDTO> entityToDtoList(List<Comentario> source){
        return source.stream().map(s -> {
            return entityToDto(s);
        }).collect(Collectors.toList());
    }

}
