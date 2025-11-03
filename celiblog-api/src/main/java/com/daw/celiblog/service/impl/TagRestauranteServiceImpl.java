package com.daw.celiblog.service.impl;

import com.daw.celiblog.db.entity.Restaurante;
import com.daw.celiblog.db.entity.TagRestaurante;
import com.daw.celiblog.db.repository.RestauranteRepository;
import com.daw.celiblog.db.repository.TagRestauranteRepository;
import com.daw.celiblog.dto.TagRestauranteDTO;
import com.daw.celiblog.dto.TagRestauranteView;
import com.daw.celiblog.service.TagRestauranteService;
import com.daw.celiblog.service.mapper.TagRestauranteMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TagRestauranteServiceImpl implements TagRestauranteService {

    private final TagRestauranteRepository tagRestauranteRepository;
    private final RestauranteRepository restauranteRepository;

    public TagRestauranteServiceImpl(TagRestauranteRepository tagRestauranteRepository, RestauranteRepository restauranteRepository) {
        this.tagRestauranteRepository = tagRestauranteRepository;
        this.restauranteRepository = restauranteRepository;
    }


    @Override
    public List<String> obtenerTodosNombresTags() {
        return this.tagRestauranteRepository.findAllTagsOrder();
    }

    @Override
    public TagRestauranteDTO crearTagRestaurante(TagRestauranteView tagRestauranteView) {
        Optional<Restaurante> restaurante = this.restauranteRepository.findById(tagRestauranteView.getIdRestaurante());
        TagRestaurante nuevoTagRestaurante = new TagRestaurante();
        if(restaurante.isPresent()){
            nuevoTagRestaurante.setRestaurante(restaurante.get());
            nuevoTagRestaurante.setNombre(tagRestauranteView.getNombre());
        }
        return TagRestauranteMapper.entityToDto(this.tagRestauranteRepository.save(nuevoTagRestaurante));
    }


}
