package com.daw.celiblog.service.mapper;

import com.daw.celiblog.db.entity.Usuario;
import com.daw.celiblog.dto.UsuarioDTO;

import java.util.List;
import java.util.stream.Collectors;


public class UsuarioMapper {
    private UsuarioDTO dto;
    private Usuario entity;
    public UsuarioMapper(Usuario entity, UsuarioDTO dto){
        this.entity = entity;
        this.dto = dto;
    }

    public static UsuarioDTO entityToDto(Usuario source){
        return new UsuarioDTO(source.getIdUsuario(), source.getEmail(), source.getNombre(), source.getPassword(), source.getRol(), source.getFechaAta());
    }

    public static Usuario dtoToEntity(UsuarioDTO source){
        return new Usuario();
    }

    public static List<Usuario> dtoToEntityList(List<UsuarioDTO> source){
        return source.stream().map(s -> {
            return dtoToEntity(s);
        }).collect(Collectors.toList());
    }

    public static List<UsuarioDTO> entityToDtoList(List<Usuario> source){
        return source.stream().map(s -> {
            return entityToDto(s);
        }).collect(Collectors.toList());
    }

}
