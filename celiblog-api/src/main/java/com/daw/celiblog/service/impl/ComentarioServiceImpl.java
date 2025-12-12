package com.daw.celiblog.service.impl;

import com.daw.celiblog.db.entity.Comentario;
import com.daw.celiblog.db.entity.Usuario;
import com.daw.celiblog.db.repository.ComentarioRepository;
import com.daw.celiblog.db.repository.FavoritoRepository;
import com.daw.celiblog.db.repository.UsuarioRepository;
import com.daw.celiblog.dto.ComentarioDTO;
import com.daw.celiblog.dto.ComentarioView;
import com.daw.celiblog.enums.ObjetoEnum;
import com.daw.celiblog.service.ComentarioService;
import com.daw.celiblog.service.UsuarioService;
import com.daw.celiblog.service.mapper.ComentarioMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ComentarioServiceImpl implements ComentarioService {
    private final ComentarioRepository comentarioRepository;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;
    private final FavoritoRepository favoritoRepository;

    public ComentarioServiceImpl(ComentarioRepository comentarioRepository, UsuarioRepository usuarioRepository, UsuarioService usuarioService, FavoritoRepository favoritoRepository) {
        this.comentarioRepository = comentarioRepository;
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
        this.favoritoRepository = favoritoRepository;
    }


    @Override
    public List<ComentarioDTO> allComentariosByObject(Authentication authentication, ObjetoEnum objeto, Long idObjeto) {
        List<ComentarioDTO>  comentarios =  ComentarioMapper.entityToDtoList(this.comentarioRepository.allComentariosByObject(objeto.toString(), idObjeto));
        if(authentication == null){
            return comentarios;
        }else{
            return getFavorits(comentarios, authentication.getName());
        }
    }

    @Override
    public ComentarioDTO add(Authentication authentication, ComentarioView comentarioView, ObjetoEnum objetoEnum) {
        Optional<Usuario> usuario = this.usuarioRepository.findByEmail(authentication.getName());
        if(usuario.isPresent()){
            Comentario comentario = new Comentario();
            comentario.setContenido(comentarioView.getContenido());
            comentario.setTitulo(comentarioView.getTitulo());
            comentario.setSubtitulo(comentarioView.getSubtitulo());
            comentario.setComentarioUrl(comentarioView.getComentarioUrl());
            comentario.setUsuario(usuario.get());
            comentario.setIdObjetoComentado(comentarioView.getIdObjetoComentado());
            comentario.setObjeto_comentado(objetoEnum);
            comentario.setValoracion(comentarioView.getValoracion());
            comentario.setFechaPublicacion(new Date());
            return ComentarioMapper.entityToDto(this.comentarioRepository.save(comentario));
        }
        return null;

    }

    @Override
    public ComentarioDTO update(Authentication authentication, ComentarioView comentarioView, Long idComentario) {
        Optional<Comentario> comentario = this.comentarioRepository.findById(idComentario);
        if(comentario.isPresent()){
            Comentario coment = comentario.get();
            coment.setContenido(comentarioView.getContenido());
            coment.setSubtitulo(comentarioView.getSubtitulo());
            coment.setComentarioUrl(comentarioView.getComentarioUrl());
            coment.setValoracion(comentarioView.getValoracion());
            return ComentarioMapper.entityToDto(this.comentarioRepository.save(coment));
        }
        return null;
    }

    @Override
    public boolean deleteById(Long idComentario) {
        Optional<Comentario> comentario = this.comentarioRepository.findById(idComentario);
        if(comentario.isPresent()){
            this.comentarioRepository.deleteById(idComentario);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public ComentarioDTO getComentarioById(Authentication authentication, Long idComentario) {
            ComentarioDTO comentario =  this.comentarioRepository
                .findById(idComentario)
                .map(ComentarioMapper::entityToDto)
                .orElse(null);

        if(authentication == null){
            return comentario;
        }else if(comentario != null){
            return getFavorit(comentario, authentication.getName());
        }
        return null;

    }

    private List<ComentarioDTO> getFavorits(List<ComentarioDTO> listado, String emailUsuarioLogin){
        Long idUsuarioLogado = this.usuarioService.getIdUsuarioLogado(emailUsuarioLogin);
        List<Long> ids = this.favoritoRepository.getIdFavoritosByTipoReferencia(idUsuarioLogado, ObjetoEnum.COMENTARIO.toString());
        return listado
                .stream()
                .peek(coment -> {
                    if(ids.contains(coment.getIdComentario())){
                        coment.setEsFavoritoUsuario(true);
                    }
                }).toList();
    }
    private ComentarioDTO getFavorit(ComentarioDTO comentario, String emailUsuarioLogin){
        Long idUsuarioLogado = this.usuarioService.getIdUsuarioLogado(emailUsuarioLogin);
        List<Long> idRestaurantesFavorits = this.favoritoRepository.getIdFavoritosByTipoReferencia(idUsuarioLogado, ObjetoEnum.RESTAURANTE.toString());
        if(idRestaurantesFavorits.contains(comentario.getIdComentario())){
            comentario.setEsFavoritoUsuario(true);
        }
        return comentario;
    }


}
