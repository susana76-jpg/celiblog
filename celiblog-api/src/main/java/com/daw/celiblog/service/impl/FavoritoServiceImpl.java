package com.daw.celiblog.service.impl;

import com.daw.celiblog.db.entity.Favorito;
import com.daw.celiblog.db.entity.Usuario;
import com.daw.celiblog.db.repository.*;
import com.daw.celiblog.dto.*;
import com.daw.celiblog.enums.ObjetoEnum;
import com.daw.celiblog.service.*;
import com.daw.celiblog.service.mapper.FavoritoMapper;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FavoritoServiceImpl implements FavoritoService {

    private final FavoritoRepository favoritoRepository;
    private final UsuarioRepository usuarioRepository;
    private final RestauranteService restauranteService;
    private final RestauranteRepository restauranteRepository;
    private final RecetaService recetaService;
    private final RecetaRepository recetaRepository;
    private final PostService postService;
    private final PostRepository postRepository;
    private final ComentarioService comentarioService;
    private final ComentarioRepository comentarioRepository;

    public FavoritoServiceImpl(FavoritoRepository favoritoRepository, UsuarioRepository usuarioRepository, RestauranteRepository restauranteRepository, RestauranteService restauranteService, RecetaRepository recetaRepository, RecetaService recetaService, ComentarioRepository comentarioRepository, RestauranteRepository restauranteRepository1, RecetaRepository recetaRepository1, PostService postService, PostRepository postRepository, ComentarioService comentarioService, ComentarioRepository comentarioRepository1) {
        this.favoritoRepository = favoritoRepository;
        this.usuarioRepository = usuarioRepository;
        this.restauranteService = restauranteService;
        this.recetaService = recetaService;
        this.restauranteRepository = restauranteRepository1;
        this.recetaRepository = recetaRepository1;
        this.postService = postService;
        this.postRepository = postRepository;
        this.comentarioService = comentarioService;
        this.comentarioRepository = comentarioRepository1;
    }


    @Override
    public FavoritoDTO addFavorito(Authentication authentication, Long idReferencia, ObjetoEnum objetoEnum) {
        Optional<Usuario> usuario = this.usuarioRepository.findByEmail(authentication.getName());
        Optional<?> objetoComentado=null;
        switch (objetoEnum.toString()){
            case "RESTAURANTE":
                objetoComentado = this.restauranteRepository.findById(idReferencia);
                break;
            case "RECETA":
                objetoComentado = this.recetaRepository.findById(idReferencia);
                break;
            case "POST":
                objetoComentado = this.postRepository.findById(idReferencia);
                break;
            case "COMENTARIO":
                objetoComentado = this.comentarioRepository.findById(idReferencia);
                break;
        }
        if(usuario.isPresent() && Objects.requireNonNull(objetoComentado).isPresent()){
            Favorito nuevoFavorito = new Favorito();
            nuevoFavorito.setIdReferencia(idReferencia);
            nuevoFavorito.setTipoReferencia(objetoEnum);
            nuevoFavorito.setUsuario(usuario.get());
            nuevoFavorito.setFecha(new Date());
            return FavoritoMapper.entityToDto(this.favoritoRepository.save(nuevoFavorito));
        }else{
            return null;
        }

    }

    @Override
    public boolean deleteFavorito(Authentication authentication, Long idReferencia, ObjetoEnum objetoEnum) {
        Optional<Usuario> usuario = this.usuarioRepository.findByEmail(authentication.getName());
        if(usuario.isPresent()){
            Optional<Favorito> favorito = this.favoritoRepository.getIdFavoritosByIdReferenciaAndTipoReferencia(usuario.get().getIdUsuario(), objetoEnum.toString(), idReferencia);
            if(favorito.isPresent()){
                this.favoritoRepository.deleteById(favorito.get().getIdFavorito());
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    @Override
    public List<?> getFavoritosByReferencia(Authentication authentication, ObjetoEnum objetoEnum) {
        Optional<Usuario> usuario = this.usuarioRepository.findByEmail(authentication.getName());

        if(usuario.isPresent()){
           List<Long> ids = this.favoritoRepository.getIdFavoritosByTipoReferencia(usuario.get().getIdUsuario(), objetoEnum.toString());
           switch (objetoEnum.toString()){
               case "RESTAURANTE":
                   List<RestauranteDTO> listRestaurantes = new ArrayList<>();
                   for(Long id: ids){
                       RestauranteDTO restauranteDTO = this.restauranteService.getById(authentication, id);
                       if(restauranteDTO != null)
                            listRestaurantes.add(restauranteDTO);
                   }
                   return listRestaurantes;

               case "RECETA":
                   List<RecetaDTO> lisRecetas = new ArrayList<>();
                   for(Long id: ids){
                       RecetaDTO recetaDTO = this.recetaService.getById(authentication, id);
                       if(recetaDTO != null)
                            lisRecetas.add(recetaDTO);
                   }
                   return lisRecetas;

               case "POST":
                   List<PostDTO> lispost = new ArrayList<>();
                   for(Long id: ids){
                       PostDTO postDTO = this.postService.getById(authentication, id);
                       if(postDTO != null)
                            lispost.add(postDTO);
                   }
                   return lispost;

               case "COMENTARIO":
                   List<ComentarioDTO> listComentario = new ArrayList<>();
                   for(Long id: ids){
                       ComentarioDTO comentarioDTO = this.comentarioService.getComentarioById(authentication, id);
                       if(comentarioDTO != null)
                            listComentario.add(comentarioDTO);
                   }
                   return listComentario;
           }
       }
        return null;
    }

    @Override
    public EstadisticaDTO getFavoritosEstadistica(Authentication authentication) {
        Optional<Usuario> usuario = this.usuarioRepository.findByEmail(authentication.getName());
        if(usuario.isPresent()){
            Long idUsuario = usuario.get().getIdUsuario();
            EstadisticaDTO estadistica = new EstadisticaDTO();
            estadistica.setNumPost(this.favoritoRepository.getIdFavoritosByTipoReferencia(idUsuario, ObjetoEnum.POST.toString()).size());
            estadistica.setNumRecetas(favoritoRepository.getIdFavoritosByTipoReferencia(idUsuario, ObjetoEnum.RECETA.toString()).size());
            estadistica.setNumRestaurantes(favoritoRepository.getIdFavoritosByTipoReferencia(idUsuario, ObjetoEnum.RESTAURANTE.toString()).size());
            estadistica.setNumComentarios(comentarioRepository.getNumComentariosByUsuario(idUsuario));//aquí se señala el número de comentarios realizados por el usuario logado
            return estadistica;
        }
        return null;
    }
}
