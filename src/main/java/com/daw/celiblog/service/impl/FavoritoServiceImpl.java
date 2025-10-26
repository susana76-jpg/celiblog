package com.daw.celiblog.service.impl;

import com.daw.celiblog.db.entity.Favorito;
import com.daw.celiblog.db.entity.Usuario;
import com.daw.celiblog.db.repository.FavoritoRepository;
import com.daw.celiblog.db.repository.UsuarioRepository;
import com.daw.celiblog.dto.FavoritoDTO;
import com.daw.celiblog.dto.FavoritoView;
import com.daw.celiblog.service.FavoritoService;
import com.daw.celiblog.service.mapper.FavoritoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoritoServiceImpl implements FavoritoService {

    private final FavoritoRepository favoritoRepository;
    private final UsuarioRepository usuarioRepository;

    public FavoritoServiceImpl(FavoritoRepository favoritoRepository, UsuarioRepository usuarioRepository) {
        this.favoritoRepository = favoritoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<FavoritoDTO> getFavoritosRestaurantesByIdUsuario(Long idUsuario) {
        return FavoritoMapper.entityToDtoList(this.favoritoRepository.getFavoritosRestaurantesByIdUsuario(idUsuario));
    }

    @Override
    public List<Long> getFavoritosIdRestaurantesByIdUsuario(Long idUsuario) {
        return this.favoritoRepository.getFavoritosIdRestaurantesByIdUsuario(idUsuario);
    }

    @Override
    public List<FavoritoDTO> getFavoritosRecetasByIdUsuario(Long idUsuario) {
        return FavoritoMapper.entityToDtoList(this.favoritoRepository.getFavoritosRecetasByIdUsuario(idUsuario));
    }

    @Override
    public List<Long> getFavoritosIdRecetasByIdUsuario(Long idUsuario) {
        return this.favoritoRepository.getFavoritosIdRecetasByIdUsuario(idUsuario);
    }

    @Override
    public List<FavoritoDTO> getFavoritosPostByIdUsuario(Long idUsuario) {
        return FavoritoMapper.entityToDtoList(this.favoritoRepository.getFavoritosPostByIdUsuario(idUsuario));
    }

    @Override
    public List<Long> getFavoritosIdPostByIdUsuario(Long idUsuario) {
        return this.favoritoRepository.getFavoritosIdPostByIdUsuario(idUsuario);
    }

    @Override
    public FavoritoDTO addFavorito(FavoritoView favoritoView) {
        Optional<Usuario> usuario = this.usuarioRepository.findById(favoritoView.getIdUsuario());
        if(usuario.isPresent()){
            Favorito nuevoFavorito = new Favorito();
            nuevoFavorito.setIdReferencia(favoritoView.getIdReferencia());
            nuevoFavorito.setTipoReferencia(favoritoView.getTipoReferencia());
            nuevoFavorito.setUsuario(usuario.get());
            this.favoritoRepository.save(nuevoFavorito);
            return FavoritoMapper.entityToDto(nuevoFavorito);
        }else{
            return null;
        }

    }

    @Override
    public boolean deleteFavorito(Long idFavorito) {
        if(this.favoritoRepository.findById(idFavorito).isPresent()){
            this.favoritoRepository.deleteById(idFavorito);
            return true;
        }else{
            return false;
        }
    }
}
