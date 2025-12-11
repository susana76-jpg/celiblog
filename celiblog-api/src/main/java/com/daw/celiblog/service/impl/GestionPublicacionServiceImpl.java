package com.daw.celiblog.service.impl;

import com.daw.celiblog.db.entity.Comentario;
import com.daw.celiblog.db.entity.Post;
import com.daw.celiblog.db.entity.Receta;
import com.daw.celiblog.db.entity.Restaurante;
import com.daw.celiblog.db.repository.ComentarioRepository;
import com.daw.celiblog.db.repository.PostRepository;
import com.daw.celiblog.db.repository.RecetaRepository;
import com.daw.celiblog.db.repository.RestauranteRepository;
import com.daw.celiblog.dto.ComentarioDTO;
import com.daw.celiblog.dto.PostDTO;
import com.daw.celiblog.dto.RecetaDTO;
import com.daw.celiblog.dto.RestauranteDTO;
import com.daw.celiblog.enums.EstadoValidacionEnum;
import com.daw.celiblog.enums.ObjetoEnum;
import com.daw.celiblog.service.*;
import com.daw.celiblog.service.mapper.ComentarioMapper;
import com.daw.celiblog.service.mapper.PostMapper;
import com.daw.celiblog.service.mapper.RecetaMapper;
import com.daw.celiblog.service.mapper.RestauranteMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GestionPublicacionServiceImpl implements GestionPublicacionService {

    private final RecetaRepository recetaRepository;
    private final RestauranteRepository restauranteRepository;
    private final PostRepository postRepository;
    private final ComentarioRepository comentarioRepository;

    public GestionPublicacionServiceImpl(RecetaService recetaService, RecetaRepository recetaRepository, RestauranteService restauranteService, RestauranteRepository restauranteRepository, PostService postService, PostRepository postRepository, ComentarioService comentarioService, ComentarioRepository comentarioRepository) {
        this.recetaRepository = recetaRepository;
        this.restauranteRepository = restauranteRepository;
        this.postRepository = postRepository;
        this.comentarioRepository = comentarioRepository;
    }

    @Override
    public List<?> getObjetosByEstado(Authentication authentication, ObjetoEnum tipoReferencia, EstadoValidacionEnum estadoValidacionEnum) {
        return switch (tipoReferencia.toString()) {
            case "RESTAURANTE" -> this.restauranteRepository.getByEstadoPublicacion(estadoValidacionEnum.toString());
            case "RECETA" -> this.recetaRepository.getByEstadoPublicacion(estadoValidacionEnum.toString());
            case "POST" -> this.postRepository.getByEstadoPublicacion(estadoValidacionEnum.toString());
            case "COMENTARIO" -> this.comentarioRepository.getByEstadoPublicacion(estadoValidacionEnum.toString());
            default -> null;
        };
    }

    @Override
    public Object updateObjetoEstadoPublicacion(Authentication authentication, Long idObjeto, ObjetoEnum tipoReferencia, EstadoValidacionEnum estado) {
        switch (tipoReferencia.toString()){
            case "RESTAURANTE":
                Optional<Restaurante> res = this.restauranteRepository.findById(idObjeto);
                if(res.isPresent()){
                    Restaurante restaurante = res.get();
                    restaurante.setEstado(estado);
                    restaurante.setFechaValidacion(new Date());
                    return RestauranteMapper.entityToDto(this.restauranteRepository.save(restaurante));
                }
            case "RECETA":
                Optional<Receta> rec = this.recetaRepository.findById(idObjeto);
                if(rec.isPresent()){
                    Receta receta = rec.get();
                    receta.setEstado(estado);
                    receta.setFechaValidacion(new Date());
                    return RecetaMapper.entityToDto(this.recetaRepository.save(receta));
                }
            case "POST":
                Optional<Post> pos = this.postRepository.findById(idObjeto);
                if(pos.isPresent()){
                    Post post = pos.get();
                    post.setEstado(estado);
                    post.setFechaValidacion(new Date());
                    return PostMapper.entityToDto(this.postRepository.save(post));
                }
            case "COMENTARIO":
                Optional<Comentario> comen = this.comentarioRepository.findById(idObjeto);
                if(comen.isPresent()){
                    Comentario comentario = comen.get();
                    comentario.setEstado(estado);
                    comentario.setFechaValidacion(new Date());
                    return ComentarioMapper.entityToDto(this.comentarioRepository.save(comentario));
                }
        }
        return null;
    }

}
