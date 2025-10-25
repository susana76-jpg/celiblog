package com.daw.celiblog.service.impl;

import com.daw.celiblog.db.entity.Receta;
import com.daw.celiblog.db.entity.TagReceta;
import com.daw.celiblog.db.repository.RecetaRepository;
import com.daw.celiblog.db.repository.TagRecetaRepository;
import com.daw.celiblog.dto.RecetaDTO;
import com.daw.celiblog.dto.TagRecetaDTO;
import com.daw.celiblog.dto.TagRecetaView;
import com.daw.celiblog.service.TagRecetaService;
import com.daw.celiblog.service.mapper.TagRecetaMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TagRecetaServiceImpl implements TagRecetaService {

    private final TagRecetaRepository tagRecetaRepository;
    private final RecetaRepository recetaRepository;

    public TagRecetaServiceImpl(TagRecetaRepository tagRecetaRepository, RecetaRepository recetaRepository) {
        this.tagRecetaRepository = tagRecetaRepository;
        this.recetaRepository = recetaRepository;
    }

    @Override
    public List<String> obtenerTodosNombresTags() {
       return this.tagRecetaRepository.findAllTagsOrder();
    }
    public TagRecetaDTO crearTagReceta(TagRecetaView tagRecetaView){
        Optional<Receta> receta = this.recetaRepository.findById(tagRecetaView.getIdReceta());
        TagReceta nuevaTagReceta = new TagReceta();
        if(receta.isPresent()){
            nuevaTagReceta.setReceta(receta.get());
            nuevaTagReceta.setNombre(tagRecetaView.getDescripcionTag());
        }
        return TagRecetaMapper.entityToDto(this.tagRecetaRepository.save(nuevaTagReceta));
    }


}
