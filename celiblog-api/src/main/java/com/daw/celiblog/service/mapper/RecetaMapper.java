package com.daw.celiblog.service.mapper;

import com.daw.celiblog.db.entity.Receta;
import com.daw.celiblog.dto.RecetaDTO;
import com.daw.celiblog.dto.UsuarioSummaryDTO;

import java.util.List;
import java.util.stream.Collectors;


public class RecetaMapper {
    private Receta entity;
    private RecetaDTO dto;

    public RecetaMapper(Receta entity, RecetaDTO dto){
        this.entity = entity;
        this.dto = dto;
    }
    public static RecetaDTO     entityToDto(Receta source){
        return new RecetaDTO(source.getIdReceta(),
            new UsuarioSummaryDTO(source.getUsuario().getEmail(), source.getUsuario().getNombre()),
            source.getDescripcion(),
            source.getFechaCreacion(),
            source.getImagenUrl(),
            source.getTitulo(),
            source.getSubtitulo(),
            source.getDificultad(),
            source.getEstado(),
            source.getFechaValidacion(),
            source.getValoracion(),
            source.getComensales(),
            source.getTiempoPreparacion(),
            source.getValorEnergetico(),
            source.getTipoComida(),
                0);
    }
    public static Receta dtoToEntity(RecetaDTO source){
        return new Receta(source.getIdReceta(),
            UsuarioMapper.dtoToEntity(source.getUsuarioDTO()),
            source.getDescripcion(),
            source.getFechaCreacion(),
            source.getImagenUrl(),
            source.getTitulo(),
            source.getSubtitulo(),
            source.getDificultad(),
            source.getEstado(),
            source.getFechaValidacion(),
            source.getValoracion(),
            source.getComensales(),
            source.getTiempoPreparacion(),
            source.getValorEnergetico(),
            source.getTipoComida());
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
