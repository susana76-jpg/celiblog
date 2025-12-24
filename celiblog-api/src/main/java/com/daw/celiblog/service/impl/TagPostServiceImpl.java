package com.daw.celiblog.service.impl;

import com.daw.celiblog.db.entity.TagPost;
import com.daw.celiblog.db.repository.FavoritoRepository;
import com.daw.celiblog.db.repository.PostRepository;
import com.daw.celiblog.db.repository.TagPostRepository;
import com.daw.celiblog.db.repository.UsuarioRepository;
import com.daw.celiblog.dto.PostDTO;
import com.daw.celiblog.dto.RecetaDTO;
import com.daw.celiblog.dto.TagRecetaView;
import com.daw.celiblog.enums.ObjetoEnum;
import com.daw.celiblog.service.TagPostService;
import com.daw.celiblog.service.UsuarioService;
import com.daw.celiblog.service.mapper.PostMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TagPostServiceImpl implements TagPostService {

    private final TagPostRepository tagPostRepository;
    private final PostRepository postRepository;
    private final UsuarioService usuarioService;
    private final FavoritoRepository favoritoRepository;

    public TagPostServiceImpl(TagPostRepository tagPostRepository, PostRepository postRepository, UsuarioRepository usuarioRepository, UsuarioService usuarioService, FavoritoRepository favoritoRepository) {
        this.tagPostRepository = tagPostRepository;
        this.postRepository = postRepository;
        this.usuarioService = usuarioService;
        this.favoritoRepository = favoritoRepository;
    }


    @Override
    public List<String> obtenerTodosNombresTags(Authentication authentication) {
       return this.tagPostRepository.findAllTagsOrder();
    }

    @Override
    public PostDTO crearTagPost(TagRecetaView tagRecetaView) {

        return null;
    }


    @Override
    public boolean eliminarByIdTag(Long idTag) {
        if(this.tagPostRepository.findById(idTag).isPresent()){
            this.tagPostRepository.deleteById(idTag);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean eliminarByIdPostAndNombreTag(Long id, String nombreTag) {
        TagPost tag = this.tagPostRepository.buscarTagByIdPostAndNombreTag(id, nombreTag.toUpperCase());
        if(this.postRepository.findById(id).isPresent() && tag != null){
            this.tagPostRepository.deleteById(tag.getIdTagPost());
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<PostDTO> buscarPostsPorNombreDeTag(Authentication authentication, String nombreTag) {
        List<PostDTO> posts =  PostMapper.entityToDtoList(this.postRepository.buscarPostsPorNombreDeTag(nombreTag.toUpperCase())) ;
        if(authentication == null){
            return posts;
        }else{
            return getFavorits(posts, authentication.getName());
        }
    }

    @Override
    public List<PostDTO> buscarPostsPorNombreDeTags(Authentication authentication, List<String> tags) {
        Set<PostDTO> posts = new HashSet<>();
        for(String tag:tags){
            posts.addAll(new HashSet<>(this.buscarPostsPorNombreDeTag(authentication, tag)));
        }
        return posts.stream().toList();
    }


    private List<PostDTO> getFavorits(List<PostDTO> listado, String emailUsuarioLogin){
        Long idUsuarioLogado = this.usuarioService.getIdUsuarioLogado(emailUsuarioLogin);
        List<Long> ids = this.favoritoRepository.getIdFavoritosByTipoReferencia(idUsuarioLogado, ObjetoEnum.POST.toString());
        return listado
                .stream()
                .peek(post -> {
                    if(ids.contains(post.getIdPost())){
                        post.setEsFavoritoUsuario(true);
                    }
                }).toList();
    }

}
