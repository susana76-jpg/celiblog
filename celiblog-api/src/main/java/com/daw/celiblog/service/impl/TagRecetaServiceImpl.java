package com.daw.celiblog.service.impl;

import com.daw.celiblog.db.entity.Receta;
import com.daw.celiblog.db.entity.TagReceta;
import com.daw.celiblog.db.repository.RecetaRepository;
import com.daw.celiblog.db.repository.TagRecetaRepository;
import com.daw.celiblog.dto.TagRecetaDTO;
import com.daw.celiblog.dto.TagRecetaView;
import com.daw.celiblog.service.TagRecetaService;
import com.daw.celiblog.service.mapper.TagRecetaMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagRecetaServiceImpl implements TagRecetaService {

    private final TagRecetaRepository tagRecetaRepository;
    private final RecetaRepository recetaRepository;

    public TagRecetaServiceImpl(TagRecetaRepository tagRecetaRepository, RecetaRepository recetaRepository) {
        this.tagRecetaRepository = tagRecetaRepository;
        this.recetaRepository = recetaRepository;
    }

    @Override
    public List<String> obtenerTodosNombresTags(Authentication authentication) {
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

    @Override
    public boolean eliminarByIdTag(Long idTag) {
        if(this.tagRecetaRepository.findById(idTag).isPresent()){
            this.tagRecetaRepository.deleteById(idTag);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean eliminarByIdRecetaAndNombreTag(Long idReceta, String nombreTag) {
        TagReceta tag = this.tagRecetaRepository.buscarTagByIdRecetaAndNombreTag(idReceta, nombreTag.toUpperCase());
        if(this.recetaRepository.findById(idReceta).isPresent() && tag != null){
            this.tagRecetaRepository.deleteById(tag.getIdTag());
            return true;
        }else{
            return false;
        }
    }


}
