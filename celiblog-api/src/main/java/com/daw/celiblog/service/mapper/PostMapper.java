package com.daw.celiblog.service.mapper;

import com.daw.celiblog.db.entity.Post;
import com.daw.celiblog.dto.PostDTO;
import com.daw.celiblog.dto.UsuarioSummaryDTO;

import java.util.List;
import java.util.stream.Collectors;


public class PostMapper {
    private Post entity;
    private PostDTO dto;

    public PostMapper(Post entity, PostDTO dto){
        this.entity = entity;
        this.dto = dto;
    }
    public static PostDTO entityToDto(Post source){
        return new PostDTO(
                source.getIdPost(),
                source.getFechaPublicacion(),
                source.getTitulo(),
                source.getSubtitulo(),
                source.getContenido(),
                new UsuarioSummaryDTO(source.getUsuario().getNombre(), source.getUsuario().getEmail()),
                UsuarioMapper.entityToDto(source.getUsuario()),
                source.getUrlPost(),
                source.getEstado(),
                source.getFechaValidacion(),
                source.getValoracion(),
                0
        );
    }
    public static Post dtoToEntity(PostDTO source){
        return new Post(
                source.getIdPost(),
                source.getFechaPublicacion(),
                source.getTitulo(),
                source.getSubtitulo(),
                source.getContenido(),
                UsuarioMapper.dtoToEntity(source.getUsuarioDTO()),
                source.getUrlPost(),
                source.getEstado(),
                source.getFechaValidacion(),
                source.getValoracion());
    }
    public static List<Post> dtoToEntityList(List<PostDTO> source){
        return source.stream().map(s -> {
            return dtoToEntity(s);
        }).collect(Collectors.toList());
    }

    public static List<PostDTO> entityToDtoList(List<Post> source){
        return source.stream().map(s -> {
            return entityToDto(s);
        }).collect(Collectors.toList());
    }

}
