package com.daw.celiblog.service;

import com.daw.celiblog.db.entity.PasoReceta;
import com.daw.celiblog.db.entity.Receta;
import com.daw.celiblog.dto.PasoRecetaDTO;
import com.daw.celiblog.dto.PasoRecetaView;

import java.util.List;

public interface PasoRecetaService {

    List<PasoRecetaDTO> obtenerPasosRecetaPorId(Long idReceta);
    PasoRecetaDTO addPasoReceta(PasoRecetaView pasoRecetaView);
    PasoRecetaDTO updatePasoReceta(PasoRecetaView pasoRecetaView);
    List<PasoRecetaView> updatePasoRecetaAll(List<PasoRecetaView> pasoRecetaView, Receta receta);
    boolean deletePasoReceta(PasoRecetaView pasoRecetaView);


}
