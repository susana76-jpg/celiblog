package com.daw.celiblog.service.impl;

import com.daw.celiblog.db.entity.Receta;
import com.daw.celiblog.db.repository.PasoRecetaRepository;
import com.daw.celiblog.db.repository.RecetaRepository;
import com.daw.celiblog.db.repository.TagRecetaRepository;
import com.daw.celiblog.dto.ComentarioDTO;
import com.daw.celiblog.dto.PasoRecetaDTO;
import com.daw.celiblog.dto.RecetaDTO;
import com.daw.celiblog.dto.TagRecetaDTO;
import com.daw.celiblog.service.RecetaService;
import com.daw.celiblog.service.mapper.PasoRecetaMapper;
import com.daw.celiblog.service.mapper.RecetaMapper;
import com.daw.celiblog.service.mapper.TagRecetaMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<PasoRecetaDTO> obtenerPasosRecetaPorId(Long idReceta) {
        return PasoRecetaMapper.entityToDtoList(this.pasoRecetaRepository.findByIdReceta(idReceta));
    }
}
