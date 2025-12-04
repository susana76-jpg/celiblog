package com.daw.celiblog.service;

import com.daw.celiblog.db.entity.VistaReceta;
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
    List<RecetaDTO> getRecetasEstadoAprobado();
    List<RecetaDTO> buscarRecetasPorNombreDeTag(String nombreTag);
    List<RecetaDTO> buscarRecetasPorNombreDeTags(List<String> tags);

    List<RecetaDTO> buscarVista(Authentication authentication, String keyword, List<TipoComidaEnum> tipoComida);





    //PRIVADO
    RecetaDTO actualizar(Authentication authentication, Long id, RecetaDTO dto);
    boolean deleteReceta(Authentication authentication, Long id);

    //PRIVADO - GESTIÓN DE PUBLICACIONES
    List<RecetaDTO> getRecetasEstadoPendiente(Authentication authentication);
    List<RecetaDTO> getRecetasEstadoRechazado(Authentication authentication);
    RecetaDTO updateEstadoPublicacionReceta(Authentication authentication, Long idReceta, EstadoValidacionEnum estado);
    List<ComentarioDTO> obtenerComentariosDeRecetaPorIdReceta(Long idReceta);


    //PROTEGIDO
    RecetaDTO crearReceta(Authentication authentication, RecetaView recetaView);
    List<RecetaDTO> obtenerRecetasFavoritasUsuario(Authentication authentication);















}
