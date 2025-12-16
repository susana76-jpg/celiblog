package com.daw.celiblog.service;

import com.daw.celiblog.dto.EstadisticaDTO;
import com.daw.celiblog.dto.RecetaDTO;
import com.daw.celiblog.enums.EstadoValidacionEnum;
import com.daw.celiblog.enums.ObjetoEnum;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface GestionPublicacionService {

    List<?> getObjetosByEstado(Authentication authentication, ObjetoEnum tipoReferencia, EstadoValidacionEnum estadoValidacionEnum);
    Object updateObjetoEstadoPublicacion(Authentication authentication, Long idObjeto, ObjetoEnum tipoReferencia, EstadoValidacionEnum estado);
    EstadisticaDTO getEstadisticasObjetos();
}
