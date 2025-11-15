package com.daw.celiblog.service.impl;

import com.daw.celiblog.db.entity.PasoReceta;
import com.daw.celiblog.db.entity.Receta;
import com.daw.celiblog.db.repository.PasoRecetaRepository;
import com.daw.celiblog.db.repository.RecetaRepository;
import com.daw.celiblog.db.repository.TagRecetaRepository;
import com.daw.celiblog.dto.*;
import com.daw.celiblog.enums.EstadoValidacion;
import com.daw.celiblog.service.RecetaService;
import com.daw.celiblog.service.mapper.PasoRecetaMapper;
import com.daw.celiblog.service.mapper.RecetaMapper;
import com.daw.celiblog.service.mapper.TagRecetaMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecetaServiceImpl implements RecetaService {
    private final RecetaRepository recetaRepository;
    private final TagRecetaRepository tagRecetaRepository;
    private final PasoRecetaRepository pasoRecetaRepository;


    public RecetaServiceImpl(RecetaRepository recetaRepository, TagRecetaRepository tagRecetaRepository, PasoRecetaRepository pasoRecetaRepository) {
        this.recetaRepository = recetaRepository;
        this.tagRecetaRepository = tagRecetaRepository;
        this.pasoRecetaRepository = pasoRecetaRepository;
    }

    @Override
    public List<RecetaDTO> obtenerTodos() {
        return RecetaMapper.entityToDtoList(recetaRepository.findAll());
    }

    @Override
    public RecetaDTO obtenerPorId(Long id) {
        return recetaRepository
                .findById(id)
                .map(RecetaMapper::entityToDto)
                .orElse(null);
    }

    @Override
    public RecetaDTO crear(RecetaDTO dto) {
        return RecetaMapper.entityToDto(recetaRepository.save(RecetaMapper.dtoToEntity(dto)));
    }

    @Override
    public boolean deleteReceta(Long id) {
        Optional<Receta> receta = this.recetaRepository.findById(id);
        if( receta.isPresent()){
            this.recetaRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public RecetaDTO actualizar(Long id, RecetaDTO dto) {
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
    public void eliminar(Long id) {
        this.recetaRepository.deleteById(id);
    }

    @Override
    public List<ComentarioDTO> obtenerComentariosDeRecetaPorIdReceta(Long idReceta) {
        return null;
    }

    @Override
    public List<TagRecetaDTO> obtenerTagsRecetaPorId(Long idReceta) {
        return  TagRecetaMapper.entityToDtoList(this.tagRecetaRepository.getTagsByIdReceta(idReceta));
    }




    @Override
    public RecetaDTO crearReceta(RecetaDTO recetaDTO) {
        return RecetaMapper.entityToDto(this.recetaRepository.save(RecetaMapper.dtoToEntity(recetaDTO)));
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

    @Override
    public List<RecetaDTO> getRecetasEstadoPendiente() {
        return RecetaMapper.entityToDtoList(this.recetaRepository.getRecetasEstadoPendiente());
    }

    @Override
    public List<RecetaDTO> getRecetasEstadoAprobado() {
        return RecetaMapper.entityToDtoList(this.recetaRepository.getRecetasEstadoAprobado());
    }

    @Override
    public List<RecetaDTO> getRecetasEstadoRechazado() {
        return RecetaMapper.entityToDtoList(this.recetaRepository.getRecetasEstadoRechazado());
    }


    @Override
    public RecetaDTO updateEstadoPublicacionReceta(Long idReceta, EstadoValidacion estado) {
        Optional<Receta> rec = this.recetaRepository.findById(idReceta);
        if(rec.isPresent()){
            Receta receta = rec.get();
            receta.setEstado(estado);
            return RecetaMapper.entityToDto(this.recetaRepository.save(receta));
        }
        return null;
    }


}
