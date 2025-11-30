package com.daw.celiblog.service.impl;

import com.daw.celiblog.db.entity.Receta;
import com.daw.celiblog.db.entity.Usuario;
import com.daw.celiblog.db.entity.VistaRecetaIngredientes;
import com.daw.celiblog.db.repository.*;
import com.daw.celiblog.dto.*;
import com.daw.celiblog.enums.EstadoValidacionEnum;
import com.daw.celiblog.enums.ObjetoEnum;
import com.daw.celiblog.enums.RolEnum;
import com.daw.celiblog.enums.TipoComidaEnum;
import com.daw.celiblog.service.RecetaService;
import com.daw.celiblog.service.UsuarioService;
import com.daw.celiblog.service.mapper.RecetaMapper;
import com.daw.celiblog.service.mapper.UsuarioMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecetaServiceImpl implements RecetaService {
    private final RecetaRepository recetaRepository;
    private final TagRecetaRepository tagRecetaRepository;
    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final FavoritoRepository favoritoRepository;




    public RecetaServiceImpl(RecetaRepository recetaRepository, TagRecetaRepository tagRecetaRepository, PasoRecetaRepository pasoRecetaRepository, UsuarioRepository usuarioRepository, UsuarioService usuarioService, UsuarioRepository usuarioRepository1, FavoritoRepository favoritoRepository) {
        this.recetaRepository = recetaRepository;
        this.tagRecetaRepository = tagRecetaRepository;
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository1;
        this.favoritoRepository = favoritoRepository;
    }

    //PÚBLICO ------------------------------------------------------------------
    @Override
    public List<RecetaDTO> getAll(Authentication authentication) {
        List<RecetaDTO> recetas = RecetaMapper.entityToDtoList(recetaRepository.findAll());
        if(authentication == null){
            return recetas;
        }else{
            return getFavorits(recetas, authentication.getName());
        }
    }

    @Override
    public RecetaDTO getById(Authentication authentication, Long id) {
        RecetaDTO receta =  recetaRepository
                .findById(id)
                .map(RecetaMapper::entityToDto)
                .orElse(null);
        if(authentication == null){
            return receta;
        }else{
            receta.setEsFavoritoUsuario(isFavorit(receta.getIdReceta(), authentication.getName()));
            return receta;
        }
    }
    @Override
    public List<RecetaDTO> getByNumComensales(Authentication authentication, int numComensales) {
        List<RecetaDTO> recetas = RecetaMapper.entityToDtoList(this.recetaRepository.getByComensales(numComensales));
        if(authentication == null){
            return recetas;
        }else{
            return getFavorits(recetas, authentication.getName());
        }
    }
    @Override
    public List<RecetaDTO> getByValoracion(Authentication authentication, int valoracion) {
        List<RecetaDTO> recetas = RecetaMapper.entityToDtoList(this.recetaRepository.getByValoracion(valoracion));
        if(authentication == null){
            return recetas;
        }else{
            return getFavorits(recetas, authentication.getName());
        }
    }
    @Override
    public List<RecetaDTO> getByTipoComida(Authentication authentication, TipoComidaEnum tipoComida) {
        List<RecetaDTO> recetas = RecetaMapper.entityToDtoList(this.recetaRepository.getByTipoComida(tipoComida));
        if(authentication == null){
            return recetas;
        }else{
            return getFavorits(recetas, authentication.getName());
        }
    }
    @Override
    public List<VistaRecetaIngredientes> getIngredientesByIdReceta(Long idReceta) {
        List<VistaRecetaIngredientes> ingredientes = this.recetaRepository.getIngredientesByIdReceta(idReceta);
        return ingredientes;
    }
    @Override
    public List<RecetaDTO> getRecetasEstadoAprobado() {
        return RecetaMapper.entityToDtoList(this.recetaRepository.getRecetasEstadoAprobado());
    }
    @Override
    public List<RecetaDTO> buscarRecetasPorNombreDeTag(String nombreTag) {
        return RecetaMapper.entityToDtoList(this.recetaRepository.buscarRecetasPorNombreDeTag(nombreTag.toUpperCase()));
    }

    @Override
    public List<RecetaDTO> buscarRecetasPorNombreDeTags(List<String> tags) {
        Set<RecetaDTO> recetas = new HashSet<>();
        for(String tag:tags){
            recetas.addAll(new HashSet<>(this.buscarRecetasPorNombreDeTag(tag)));
        }
        return recetas.stream().toList();
    }
    //fin PÚBL  ICO ------------------------------------------------------------------



    //PROTEGIDO -----------------------------------------------------------------------
    @Override
    public RecetaDTO crearReceta(Authentication authentication, RecetaView recetaView) {

            Receta receta = new Receta();
            receta.setTitulo(recetaView.getTitulo());
            receta.setSubtitulo(recetaView.getSubtitulo());
            receta.setDescripcion(recetaView.getDescripcion());
            receta.setDificultad(recetaView.getDificultad());
            receta.setValoracion(recetaView.getValoracion());
            receta.setTipoComida(recetaView.getTipoComida());
            receta.setComensales(recetaView.getComensales());
            receta.setImagenUrl(recetaView.getImagenUrl());
            receta.setTiempoPreparacion(recetaView.getTiempoPreparacion());
            receta.setValorEnergetico(recetaView.getValorEnergetico());
            Optional<Usuario> user = this.usuarioRepository.findByEmail(authentication.getName());
            user.ifPresent(receta::setUsuario);

            return RecetaMapper.entityToDto(this.recetaRepository.save(receta));

    }
    @Override
    public List<RecetaDTO> obtenerRecetasFavoritasUsuario(Authentication authentication) {

        if(authentication != null && authentication.getAuthorities().contains(RolEnum.VISITOR.toString())){
            Long idUsuarioLogado = this.usuarioService.getIdUsuarioLogado(authentication.getName());
            List<Long> listaRecetasFavoritas = this.favoritoRepository.getIdFavoritosByTipoReferencia(idUsuarioLogado, ObjetoEnum.RECETA.toString());

            return RecetaMapper.entityToDtoList(this.recetaRepository.findAll())
                    .stream()
                    .peek(receta -> {
                        if(listaRecetasFavoritas.contains(receta.getIdReceta())){
                            receta.setEsFavoritoUsuario(true);
                        }
                    }).toList();
        }
        return null;
    }
//fin PROTEGIDO -----------------------------------------------------------------------



    //PRIVADO ----------------------------------------------------------------------------

    @Override
    public RecetaDTO actualizar(Authentication authentication, Long id, RecetaDTO dto) {
        Optional<Receta> rec = this.recetaRepository.findById(id);
        if(rec.isPresent()){
            Receta receta = rec.get();
            receta.setDescripcion(dto.getDescripcion());
            receta.setFechaCreacion(dto.getFechaCreacion());
            receta.setTitulo(dto.getTitulo());
            receta.setImagenUrl(dto.getImagenUrl());

            return RecetaMapper.entityToDto(this.recetaRepository.save(receta));
        }
        return null;
    }
    @Override
    public boolean deleteReceta(Authentication authentication, Long id) {
        Optional<Receta> receta = this.recetaRepository.findById(id);
        if( receta.isPresent()){
            this.recetaRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }


    private List<RecetaDTO> getFavorits(List<RecetaDTO> listado, String emailUsuarioLogin){
        Long idUsuarioLogado = this.usuarioService.getIdUsuarioLogado(emailUsuarioLogin);
        List<Long> idRecetasFavoritas = this.favoritoRepository.getIdFavoritosByTipoReferencia(idUsuarioLogado, ObjetoEnum.RECETA.toString());
        return listado
                .stream()
                .peek(receta -> {
                    if(idRecetasFavoritas.contains(receta.getIdReceta())){
                        receta.setEsFavoritoUsuario(true);
                    }
                }).toList();
    }

    private boolean isFavorit(Long idReceta, String emailUsuarioLogin){
        Long idUsuarioLogado = this.usuarioService.getIdUsuarioLogado(emailUsuarioLogin);
        return (this.favoritoRepository.getIdFavoritosByTipoReferencia(idUsuarioLogado, ObjetoEnum.RECETA.toString())).contains(idReceta);
    }





















    @Override
    public List<ComentarioDTO> obtenerComentariosDeRecetaPorIdReceta(Long idReceta) {
        return null;
    }





    @Override
    public List<RecetaDTO> getRecetasEstadoPendiente(Authentication authentication) {
        return RecetaMapper.entityToDtoList(this.recetaRepository.getRecetasEstadoPendiente());
    }


    @Override
    public List<RecetaDTO> getRecetasEstadoRechazado(Authentication authentication) {
        return RecetaMapper.entityToDtoList(this.recetaRepository.getRecetasEstadoRechazado());
    }


    @Override
    public RecetaDTO updateEstadoPublicacionReceta(Authentication authentication, Long idReceta, EstadoValidacionEnum estado) {
        Optional<Receta> rec = this.recetaRepository.findById(idReceta);
        if(rec.isPresent()){
            Receta receta = rec.get();
            receta.setEstado(estado);
            return RecetaMapper.entityToDto(this.recetaRepository.save(receta));
        }
        return null;
    }




    //solo ADMINISTRADOR



}
