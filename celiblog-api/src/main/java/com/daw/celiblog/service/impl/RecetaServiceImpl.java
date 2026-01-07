package com.daw.celiblog.service.impl;

import com.daw.celiblog.db.entity.*;
import com.daw.celiblog.db.repository.*;
import com.daw.celiblog.dto.*;
import com.daw.celiblog.enums.EstadoValidacionEnum;
import com.daw.celiblog.enums.ObjetoEnum;
import com.daw.celiblog.enums.TipoComidaEnum;
import com.daw.celiblog.service.IngredienteService;
import com.daw.celiblog.service.PasoRecetaService;
import com.daw.celiblog.service.RecetaService;
import com.daw.celiblog.service.UsuarioService;
import com.daw.celiblog.service.mapper.RecetaMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecetaServiceImpl implements RecetaService {
    private final RecetaRepository recetaRepository;
    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final FavoritoRepository favoritoRepository;
    private final VistaRecetaRepository vistaRecetaRepository;
    private final PasoRecetaService pasoRecetaService;
    private final PasoRecetaRepository pasoRecetaRepository;
    private final IngredienteService ingredienteService;
    private final IngredienteRepository ingredienteRepository;
    private final ComentarioRepository comentarioRepository;


    public RecetaServiceImpl(RecetaRepository recetaRepository, TagRecetaRepository tagRecetaRepository, PasoRecetaRepository pasoRecetaRepository, UsuarioRepository usuarioRepository, UsuarioService usuarioService, UsuarioRepository usuarioRepository1, FavoritoRepository favoritoRepository, VistaRecetaRepository vistaRecetaRepository, PasoRecetaService pasoRecetaService, PasoRecetaRepository pasoRecetaRepository1, IngredienteService ingredienteService, IngredienteRepository ingredienteRepository, ComentarioRepository comentarioRepository) {
        this.recetaRepository = recetaRepository;
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository1;
        this.favoritoRepository = favoritoRepository;
        this.vistaRecetaRepository = vistaRecetaRepository;
        this.pasoRecetaService = pasoRecetaService;
        this.pasoRecetaRepository = pasoRecetaRepository1;
        this.ingredienteService = ingredienteService;
        this.ingredienteRepository = ingredienteRepository;
        this.comentarioRepository = comentarioRepository;
    }

    @Override
    public List<RecetaDTO> getAll(Authentication authentication) {
        List<RecetaDTO> recetas = RecetaMapper
                .entityToDtoList(recetaRepository.getByEstadoPublicacion(EstadoValidacionEnum.APROBADO.toString()))
                .stream()
                .map(receta -> {
                    setValoracionMedia(receta);
                    return receta;
                })
                .toList();
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
            .map(rec -> {
                setValoracionMedia(rec);
                return rec;
            })
                .orElse(null);

        if (authentication != null) {
            assert receta != null;
            receta.setEsFavoritoUsuario(isFavorit(receta.getIdReceta(), authentication.getName()));
        }
        return receta;
    }
    @Override
    public List<RecetaDTO> getByNumComensales(Authentication authentication, int numComensales) {
        List<RecetaDTO> recetas = RecetaMapper.entityToDtoList(this.recetaRepository.getByComensales(numComensales))
                .stream().peek(this::setValoracionMedia).toList();
        if(authentication == null){
            return recetas;
        }else{
            return getFavorits(recetas, authentication.getName());
        }
    }
    @Override
    public List<RecetaDTO> getByValoracion(Authentication authentication, int valoracion) {
        List<RecetaDTO> recetas = RecetaMapper.entityToDtoList(this.recetaRepository.getByValoracion(valoracion))
                .stream().peek(this::setValoracionMedia).toList();
        if(authentication == null){
            return recetas;
        }else{
            return getFavorits(recetas, authentication.getName());
        }
    }
    @Override
    public List<RecetaDTO> getByTipoComida(Authentication authentication, TipoComidaEnum tipoComida) {
        List<RecetaDTO> recetas = RecetaMapper.entityToDtoList(this.recetaRepository.getByTipoComida(tipoComida))
                .stream().peek(this::setValoracionMedia).toList();
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
    public List<RecetaDTO> buscarRecetasPorNombreDeTag(Authentication authentication, String nombreTag) {
        List<RecetaDTO> recetas =  RecetaMapper.entityToDtoList(this.recetaRepository.buscarRecetasPorNombreDeTag(nombreTag.toUpperCase()))
                .stream().peek(this::setValoracionMedia).toList();
        if(authentication == null){
            return recetas;
        }else{
            return getFavorits(recetas, authentication.getName());
        }
    }

    @Override
    public List<RecetaDTO> buscarRecetasPorNombreDeTags(Authentication authentication, List<String> tags) {
        Set<RecetaDTO> recetas = new HashSet<>();
        for(String tag:tags){
            recetas.addAll(new HashSet<>(this.buscarRecetasPorNombreDeTag(authentication, tag)));
        }
        return recetas.stream().toList()
                .stream().peek(this::setValoracionMedia).toList();
    }

    @Override
    public List<RecetaDTO> buscarVista(Authentication authentication, String keyword, List<TipoComidaEnum> tipoComida) {
        List<Long> recetas = this.vistaRecetaRepository.buscarVista(keyword);
        List<RecetaDTO> recetasDTO =
                recetas.stream()
                    .map(idReceta -> {
                        return getById(authentication, idReceta);
                    }).toList();

        if(tipoComida != null){
            return recetasDTO
                .stream()
                .filter(receta -> tipoComida.contains(receta.getTipoComida()))
                .toList();
        }else{
            return recetasDTO;
        }

    }
    @Override
    public RecetaDTO add(Authentication authentication, RecetaView recetaView) {

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
            receta.setFechaCreacion(new Date());
            receta.setValorEnergetico(recetaView.getValorEnergetico());
            Optional<Usuario> user = this.usuarioRepository.findByEmail(authentication.getName());
            user.ifPresent(receta::setUsuario);
            return RecetaMapper.entityToDto(this.recetaRepository.save(receta));
    }

    @Override
    public RecetaDTO update(Authentication authentication, RecetaView recetaView) {
        Optional<Receta> rec = this.recetaRepository.findById(recetaView.getIdReceta());
        if(rec.isPresent()){
            Receta receta = rec.get();
            if(recetaView.getTitulo() != null && !recetaView.getTitulo().equals("string")){
                receta.setTitulo(recetaView.getTitulo());
            }
            if(recetaView.getSubtitulo() != null && !recetaView.getSubtitulo().equals("string")){
                receta.setSubtitulo(recetaView.getSubtitulo());
            }
            if(recetaView.getDescripcion() != null && !recetaView.getDescripcion().equals("string")){
                receta.setDescripcion(recetaView.getDescripcion());
            }
            if(recetaView.getImagenUrl()!=null && !recetaView.getImagenUrl().equals("string")){
                receta.setImagenUrl(recetaView.getImagenUrl());
            }
            if(recetaView.getDificultad() != null && !recetaView.getDificultad().equals("string")){
                receta.setDificultad(recetaView.getDificultad());
            }
            if(recetaView.getValorEnergetico() != receta.getValorEnergetico()){
                receta.setValorEnergetico(recetaView.getValorEnergetico());
            }
            if(recetaView.getTipoComida() != receta.getTipoComida()){
                receta.setTipoComida(recetaView.getTipoComida());
            }
            if(recetaView.getComensales() != receta.getComensales()){
                receta.setComensales(recetaView.getComensales());
            }

            return RecetaMapper.entityToDto(this.recetaRepository.save(receta));
        }
        return null;
    }

    @Override
    public RecetaCompletaView updateAll(Authentication authentication, RecetaCompletaView recetaCompletaView) {

        Optional<Receta> rec = this.recetaRepository.findById(recetaCompletaView.getRecetaView().getIdReceta());
        RecetaDTO recetaDTO = this.update(authentication, recetaCompletaView.getRecetaView());

        if( recetaDTO != null){
            RecetaCompletaView nuevaReceta = new RecetaCompletaView();
            RecetaView recetaView = new RecetaView(recetaDTO.getIdReceta(),
                    recetaDTO.getDescripcion(),
                    recetaDTO.getImagenUrl(),
                    recetaDTO.getDificultad(),
                    recetaDTO.getTitulo(),
                    recetaDTO.getSubtitulo(),
                    recetaDTO.getValoracion(),
                    recetaDTO.getComensales(),
                    recetaDTO.getTiempoPreparacion(),
                    recetaDTO.getValorEnergetico(),
                    recetaDTO.getTipoComida());
            List<IngredienteView> ingredientes = this.ingredienteService.updateAll(authentication, recetaCompletaView.getIngredientes(), rec.get());
            List<PasoRecetaView> pasos = this.pasoRecetaService.updatePasoRecetaAll(recetaCompletaView.getPasos(), rec.get());
            return new RecetaCompletaView(recetaView, ingredientes, pasos);
        }

        return null;
    }

    @Override
    public boolean deleteById(Authentication authentication, Long id) {
        Optional<Receta> receta = this.recetaRepository.findById(id);
        if( receta.isPresent()){
            List<VistaRecetaIngredientes> ingredientes = getIngredientesByIdReceta(receta.get().getIdReceta());
            List<PasoRecetaDTO> pasos = this.pasoRecetaService.obtenerPasosRecetaPorId(receta.get().getIdReceta());
            for(VistaRecetaIngredientes ingrediente : ingredientes){
                this.ingredienteRepository.deleteById(ingrediente.getIdIngrediente());
            }
            for(PasoRecetaDTO paso: pasos){
                this.pasoRecetaRepository.deleteById(paso.getIdPaso());
            }
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

    private void setValoracionMedia(RecetaDTO receta){
        int valor = this.comentarioRepository.getValoracionObjeto(receta.getIdReceta(), ObjetoEnum.RECETA.toString());

        receta.setValoracionMedia(
            valor
        );
    }

}
