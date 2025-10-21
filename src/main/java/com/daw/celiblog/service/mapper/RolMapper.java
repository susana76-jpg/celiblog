package com.daw.celiblog.service.mapper;

import com.daw.celiblog.db.entity.Rol;
import com.daw.celiblog.dto.RolDTO;

import java.util.List;
import java.util.stream.Collectors;


public class RolMapper {
    private RolDTO dto;
    private Rol entity;
    public RolMapper(Rol entity, RolDTO dto){
        this.entity = entity;
        this.dto = dto;
    }

    public static RolDTO entityToDto(Rol source){
        return new RolDTO(source.getIdRol(), source.getNombre());
    }

    public static Rol dtoToEntity(RolDTO source){
        return new Rol(source.getIdRol(), source.getNombre());
    }

    public static List<Rol> dtoToEntityList(List<RolDTO> source){
        return source.stream().map(s -> {
            return dtoToEntity(s);
        }).collect(Collectors.toList());
    }

    public static List<RolDTO> entityToDtoList(List<Rol> source){
        return source.stream().map(s -> {
            return entityToDto(s);
        }).collect(Collectors.toList());
    }

}
