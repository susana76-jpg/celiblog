package com.daw.celiblog.service.mapper;

import com.daw.celiblog.db.entity.Restaurante;
import com.daw.celiblog.dto.RestauranteDTO;

import java.util.List;
import java.util.stream.Collectors;


public class RestauranteMapper{
    private RestauranteDTO dto;
    private Restaurante entity;
    public RestauranteMapper(Restaurante entity, RestauranteDTO dto){
        this.entity = entity;
        this.dto = dto;
    }

    public static RestauranteDTO entityToDto(Restaurante source){
        return new RestauranteDTO(
                source.getIdRestaurante(),
                source.getFechaPublicacion(),
                UsuarioMapper.entityToDto(source.getUsuario()) ,
                source.getDescripcion(),
                source.getDireccion(),
                source.getImagenUrl(),
                source.getNombre(),
                source.getUbicacion(),
                source.getCodigoPostal(),
                source.getLatitud(),
                source.getLongitud(),
                source.getUrlWeb(),
                source.getTelefono(),
                source.getEmail(),
                source.getValoracion(),
                source.getEstado(),
                source.getFechaValidacion());
    }

    public static Restaurante dtoToEntity(RestauranteDTO source){
        return new Restaurante(  source.getIdRestaurante(),
                source.getFechaPublicacion(),
                UsuarioMapper.dtoToEntity(source.getUsuarioDTO()) ,
                source.getDescripcion(),
                source.getDireccion(),
                source.getImagenUrl(),
                source.getNombre(),
                source.getUbicacion(),
                source.getCodigoPostal(),
                source.getLatitud(),
                source.getLongitud(),
                source.getUrlWeb(),
                source.getTelefono(),
                source.getEmail(),
                source.getValoracion(),
                source.getEstado(),
                source.getFechaValidacion());

    }

    public static List<Restaurante> dtoToEntityList(List<RestauranteDTO> source){
        return source.stream().map(s -> {
            return dtoToEntity(s);
        }).collect(Collectors.toList());
    }

    public static List<RestauranteDTO> entityToDtoList(List<Restaurante> source){
        return source.stream().map(s -> {
            return entityToDto(s);
        }).collect(Collectors.toList());
    }

}
