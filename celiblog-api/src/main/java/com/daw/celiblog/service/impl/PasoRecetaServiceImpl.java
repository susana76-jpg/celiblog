package com.daw.celiblog.service.impl;

import com.daw.celiblog.db.entity.PasoReceta;
import com.daw.celiblog.db.entity.Receta;
import com.daw.celiblog.db.repository.PasoRecetaRepository;
import com.daw.celiblog.db.repository.RecetaRepository;
import com.daw.celiblog.dto.PasoRecetaDTO;
import com.daw.celiblog.dto.PasoRecetaView;
import com.daw.celiblog.service.PasoRecetaService;
import com.daw.celiblog.service.mapper.PasoRecetaMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PasoRecetaServiceImpl implements PasoRecetaService {
    private final PasoRecetaRepository pasoRecetaRepository;
    private final RecetaRepository recetaRepository;

    public PasoRecetaServiceImpl(PasoRecetaRepository pasoRecetaRepository, RecetaRepository recetaRepository) {
        this.pasoRecetaRepository = pasoRecetaRepository;
        this.recetaRepository = recetaRepository;
    }

    @Override
    public List<PasoRecetaDTO> obtenerPasosRecetaPorId(Long idReceta) {
        return PasoRecetaMapper.entityToDtoList(this.pasoRecetaRepository.findByIdReceta(idReceta));
    }

    @Override
    public PasoRecetaDTO addPasoReceta(PasoRecetaView pasoRecetaView) {
        Optional<Receta> receta = this.recetaRepository.findById(pasoRecetaView.getIdReceta());
        if(receta.isPresent()){
            PasoReceta nuevoPasoReceta = new PasoReceta(pasoRecetaView.getDescripcion(), pasoRecetaView.getOrden(), receta.get());
            return  PasoRecetaMapper.entityToDto(this.pasoRecetaRepository.save(nuevoPasoReceta));
        }
        return null;
    }

    @Override
    public PasoRecetaDTO updatePasoReceta(PasoRecetaView pasoRecetaView) {
        Optional<Receta> receta = this.recetaRepository.findById(pasoRecetaView.getIdReceta());
        PasoReceta pasoOld = this.pasoRecetaRepository.findPasoByIdRecetaAndOrden(pasoRecetaView.getIdReceta(), pasoRecetaView.getOrden());
        if(receta.isPresent() && pasoOld != null){
            PasoReceta nuevoPasoReceta = pasoOld;
            nuevoPasoReceta.setDescripcion(pasoRecetaView.getDescripcion());
            nuevoPasoReceta.setOrden(pasoRecetaView.getOrden());
            return PasoRecetaMapper.entityToDto(this.pasoRecetaRepository.save(nuevoPasoReceta));
        }else{
            return null;
        }
    }

    @Override
    public boolean deletePasoReceta(PasoRecetaView pasoRecetaView) {
        PasoReceta pasoReceta = this.pasoRecetaRepository.findPasoByIdRecetaAndOrden(pasoRecetaView.getIdReceta(), pasoRecetaView.getOrden());
        if(pasoReceta != null){
            this.pasoRecetaRepository.deleteById(pasoReceta.getIdPaso());
            return true;
        }else{
            return false;
        }
    }
}
