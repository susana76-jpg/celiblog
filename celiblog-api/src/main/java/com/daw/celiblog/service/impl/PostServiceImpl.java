package com.daw.celiblog.service.impl;

import com.daw.celiblog.db.entity.Post;
import com.daw.celiblog.db.entity.Restaurante;
import com.daw.celiblog.db.entity.Usuario;
import com.daw.celiblog.db.repository.FavoritoRepository;
import com.daw.celiblog.db.repository.PostRepository;
import com.daw.celiblog.db.repository.UsuarioRepository;
import com.daw.celiblog.dto.PostDTO;
import com.daw.celiblog.dto.PostView;
import com.daw.celiblog.dto.RecetaDTO;
import com.daw.celiblog.enums.EstadoValidacionEnum;
import com.daw.celiblog.enums.ObjetoEnum;
import com.daw.celiblog.service.PostService;
import com.daw.celiblog.service.UsuarioService;
import com.daw.celiblog.service.mapper.PostMapper;
import com.daw.celiblog.service.mapper.RestauranteMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final FavoritoRepository favoritoRepository;

    public PostServiceImpl(PostRepository postRepository, UsuarioService usuarioService, UsuarioRepository usuarioRepository, FavoritoRepository favoritoRepository) {
        this.postRepository = postRepository;
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
        this.favoritoRepository = favoritoRepository;
    }

    @Override
    public List<PostDTO> all(Authentication authentication) {
        List<PostDTO> post =  this.postRepository.getByEstadoPublicacion(EstadoValidacionEnum.APROBADO.toString())
                .stream()
                .map(PostMapper::entityToDto)
                .toList();
        if(authentication == null){
            return post;
        }else{
            return getFavorits(post, authentication.getName());
        }

    }

    @Override
    public PostDTO getById(Authentication authentication, Long id) {
        PostDTO post =  this.postRepository
                .findById(id)
                .map(PostMapper::entityToDto)
                .orElse(null);
        if(authentication == null){
            return post;
        }else if(post != null){
            return getFavorit(post, authentication.getName());
        }
        return null;
    }

    @Override
    public PostDTO add(Authentication authentication, PostView postView){
        Optional<Usuario> usuario = this.usuarioRepository.findByEmail(authentication.getName());
        if(usuario.isPresent()){
            Post post = new Post();
            post.setTitulo(postView.getTitulo());
            post.setSubtitulo(postView.getSubtitulo());
            post.setContenido(postView.getContenido());
            post.setUrlPost(postView.getUrlPost());
            post.setValoracion(postView.getValoracion());
            post.setUsuario(usuario.get());
            post.setFechaPublicacion(new Date());
            return PostMapper.entityToDto(this.postRepository.save(post));
        }
        return null;
    }

    @Override
    public PostDTO update(Long id, PostView postView){
        Optional<Post> pos = this.postRepository.findById(id);
        if(pos.isPresent()){
            Post post = pos.get();
            if(postView.getTitulo() != null){
                post.setTitulo(postView.getTitulo());
            }
            if(postView.getSubtitulo() != null){
                post.setSubtitulo(postView.getSubtitulo());
            }
            if(postView.getUrlPost() != null){
                post.setUrlPost(postView.getUrlPost());
            }
            if(post.getValoracion() != postView.getValoracion()){
                post.setValoracion(postView.getValoracion());
            }
            return PostMapper.entityToDto(this.postRepository.save(post));
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        if(this.postRepository.findById(id).isPresent()){
            this.postRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<PostDTO> buscar(Authentication authentication, String keyword) {
        List<Long> posts = this.postRepository.buscar(keyword);
        return posts.stream()
            .map(idPost -> {
                return getById(authentication, idPost);
            }).toList();
    }


    private List<PostDTO> getFavorits(List<PostDTO> listado, String emailUsuarioLogin){
        Long idUsuarioLogado = this.usuarioService.getIdUsuarioLogado(emailUsuarioLogin);
        List<Long> ids = this.favoritoRepository.getIdFavoritosByTipoReferencia(idUsuarioLogado, ObjetoEnum.POST.toString());
        return listado
                .stream()
                .peek(po -> {
                    if(ids.contains(po.getIdPost())){
                        po.setEsFavoritoUsuario(true);
                    }
                }).toList();
    }
    private PostDTO getFavorit(PostDTO post, String emailUsuarioLogin){
        Long idUsuarioLogado = this.usuarioService.getIdUsuarioLogado(emailUsuarioLogin);
        List<Long> ids = this.favoritoRepository.getIdFavoritosByTipoReferencia(idUsuarioLogado, ObjetoEnum.POST.toString());
        if(ids.contains(post.getIdPost())){
            post.setEsFavoritoUsuario(true);
        }
        return post;
    }

    private boolean isFavorit(Long id, String emailUsuarioLogin){
        Long idUsuarioLogado = this.usuarioService.getIdUsuarioLogado(emailUsuarioLogin);
        return (this.favoritoRepository.getIdFavoritosByTipoReferencia(idUsuarioLogado, ObjetoEnum.POST.toString())).contains(id);
    }
}
