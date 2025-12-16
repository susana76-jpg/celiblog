package com.daw.celiblog.service.impl;

import com.daw.celiblog.db.entity.Receta;
import com.daw.celiblog.db.entity.TagPost;
import com.daw.celiblog.db.entity.TagReceta;
import com.daw.celiblog.db.repository.PostRepository;
import com.daw.celiblog.db.repository.TagPostRepository;
import com.daw.celiblog.dto.PostDTO;
import com.daw.celiblog.dto.TagRecetaDTO;
import com.daw.celiblog.dto.TagRecetaView;
import com.daw.celiblog.service.TagPostService;
import com.daw.celiblog.service.mapper.TagRecetaMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagPostServiceImpl implements TagPostService {

    private final TagPostRepository tagPostRepository;
    private final PostRepository postRepository;

    public TagPostServiceImpl(TagPostRepository tagPostRepository, PostRepository postRepository) {
        this.tagPostRepository = tagPostRepository;
        this.postRepository = postRepository;
    }


    @Override
    public List<String> obtenerTodosNombresTags() {
       return this.tagPostRepository.findAllTagsOrder();
    }

    @Override
    public PostDTO crearTagPost(TagRecetaView tagRecetaView) {
        return null;
    }



    /*public TagPostDTO crearTagReceta(TagPostView tagPostView){
        Optional<Receta> receta = this.postRepository.findById(tagRecetaView.getIdReceta());
        TagReceta nuevaTagReceta = new TagReceta();
        if(receta.isPresent()){
            nuevaTagReceta.setReceta(receta.get());
            nuevaTagReceta.setNombre(tagRecetaView.getDescripcionTag());
        }
        return TagRecetaMapper.entityToDto(this.tagPostRepository.save(nuevaTagReceta));
    }*/

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


}
