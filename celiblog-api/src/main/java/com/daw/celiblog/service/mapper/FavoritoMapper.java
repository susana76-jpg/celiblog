package com.daw.celiblog.service.mapper;

import com.daw.celiblog.db.entity.Favorito;
import com.daw.celiblog.db.entity.Favorito;
import com.daw.celiblog.db.entity.Usuario;
import com.daw.celiblog.dto.FavoritoDTO;
import com.daw.celiblog.dto.FavoritoDTO;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class FavoritoMapper {
    private FavoritoDTO dto;
    private Favorito entity;
    public FavoritoMapper(Favorito entity, FavoritoDTO dto){
        this.entity = entity;
        this.dto = dto;
    }

    public static FavoritoDTO entityToDto(Favorito source){
        return new FavoritoDTO(source.getIdFavorito(), source.getUsuario().getIdUsuario(), source.getTipoReferencia(), source.getIdReferencia(), source.getFecha());
    }

    public static Favorito dtoToEntity(FavoritoDTO source){
        return new Favorito(source.getIdFavorito(), UsuarioMapper.dtoToEntity(source.getUsuario()) , source.getTipoReferencia(), source.getIdReferencia(), source.getFecha());
    }


    public static List<Favorito> dtoToEntityList(List<FavoritoDTO> source){
        return source.stream().map(s -> {
            return dtoToEntity(s);
        }).collect(Collectors.toList());
    }

    public static List<FavoritoDTO> entityToDtoList(List<Favorito> source){
        return source.stream().map(s -> {
            return entityToDto(s);
        }).collect(Collectors.toList());
    }

}
