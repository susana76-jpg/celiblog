package com.daw.celiblog.service;

import com.daw.celiblog.db.entity.VistaRecetaIngredientes;
import com.daw.celiblog.dto.*;
import com.daw.celiblog.enums.EstadoValidacionEnum;
import com.daw.celiblog.enums.TipoComidaEnum;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface RecetaService {

    //PÚBLICO
    List<RecetaDTO> getAll(Authentication authentication);
    RecetaDTO getById(Authentication authentication, Long id);
    List<RecetaDTO> getByNumComensales(Authentication authentication, int numComensales);
    List<RecetaDTO> getByValoracion(Authentication authentication, int valoracion);
    List<RecetaDTO> getByTipoComida(Authentication authentication, TipoComidaEnum tipoComida);
    List<VistaRecetaIngredientes> getIngredientesByIdReceta(Long idReceta);
    List<RecetaDTO> buscarRecetasPorNombreDeTag(String nombreTag);
    List<RecetaDTO> buscarRecetasPorNombreDeTags(List<String> tags);
    List<RecetaDTO> buscarVista(Authentication authentication, String keyword, List<TipoComidaEnum> tipoComida);


    //PRIVADO
    RecetaDTO update(Authentication authentication, RecetaView view);
    boolean deleteById(Authentication authentication, Long id);


    //PROTEGIDO
    RecetaDTO add(Authentication authentication, RecetaView recetaView);















}
