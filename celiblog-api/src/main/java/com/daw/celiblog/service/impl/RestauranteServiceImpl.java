package com.daw.celiblog.service.impl;

import com.daw.celiblog.db.entity.Restaurante;
import com.daw.celiblog.db.entity.Usuario;
import com.daw.celiblog.db.repository.*;
import com.daw.celiblog.dto.RecetaDTO;
import com.daw.celiblog.dto.RestauranteDTO;
import com.daw.celiblog.dto.RestauranteView;
import com.daw.celiblog.dto.TagRestauranteDTO;
import com.daw.celiblog.enums.EstadoValidacionEnum;
import com.daw.celiblog.enums.ObjetoEnum;
import com.daw.celiblog.enums.TipoRestauranteEnum;
import com.daw.celiblog.service.GeolocalizacionService;
import com.daw.celiblog.service.RestauranteService;
import com.daw.celiblog.service.UsuarioService;
import com.daw.celiblog.service.mapper.RestauranteMapper;
import com.daw.celiblog.service.mapper.TagRestauranteMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RestauranteServiceImpl implements RestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final TagRestauranteRepository tagRestauranteRepository;
    private final UsuarioRepository usuarioRepository;
    private final GeolocalizacionService geolocalizacionService;
    private final UsuarioService usuarioService;
    private final FavoritoRepository favoritoRepository;
    private final ComentarioRepository comentarioRepository;



    public RestauranteServiceImpl(RestauranteRepository restauranteRepository, TagRestauranteRepository tagRestauranteRepository, UsuarioRepository usuarioRepository, GeolocalizacionService geolocalizacionService, UsuarioService usuarioService, FavoritoRepository favoritoRepository, ComentarioRepository comentarioRepository) {
        this.restauranteRepository = restauranteRepository;
        this.tagRestauranteRepository = tagRestauranteRepository;
        this.usuarioRepository = usuarioRepository;
        this.geolocalizacionService = geolocalizacionService;
        this.usuarioService = usuarioService;
        this.favoritoRepository = favoritoRepository;
        this.comentarioRepository = comentarioRepository;
    }


    @Override
    public List<RestauranteDTO> all(Authentication authentication) {
        List<RestauranteDTO> restaurantes =  restauranteRepository.getByEstadoPublicacion(EstadoValidacionEnum.APROBADO.toString()).stream()
                .map(RestauranteMapper::entityToDto)
                .peek(this::setValoracionMedia)
                .toList();
        if(authentication == null){
            return restaurantes;
        }else{
            return getFavorits(restaurantes, authentication.getName());
        }

   }

    @Override
    public RestauranteDTO getById(Authentication authentication, Long id) {
        RestauranteDTO restaurante =  restauranteRepository
                .findById(id)
                .map(RestauranteMapper::entityToDto)
                .map(res -> {
                    setValoracionMedia(res);
                    return res;
                })
                .orElse(null);
        if(authentication != null && restaurante != null){
            return getFavorit(restaurante, authentication.getName());
        }
        return restaurante;
    }

    @Override
    public RestauranteDTO add(Authentication authentication, RestauranteView restauranteView) throws JsonProcessingException {
        Optional<Usuario> usuario = this.usuarioRepository.findByEmail(authentication.getName());
        if(usuario.isPresent()){
            Restaurante restaurante = new Restaurante();
            restaurante.setTitulo(restauranteView.getTitulo());
            restaurante.setSubtitulo(restauranteView.getSubtitulo());
            restaurante.setNombre(restauranteView.getNombre());
            restaurante.setDescripcion(restauranteView.getDescripcion());
            restaurante.setUsuario(usuario.get());
            restaurante.setEmail(restauranteView.getEmail());
            restaurante.setFechaPublicacion(new Date());
            restaurante.setDescripcion(restauranteView.getDireccion());
            restaurante.setDireccion(restauranteView.getDireccion());
            restaurante.setTipoRestaurante(restauranteView.getTipoRestaurante());
            //geolocalización del restaurante
            double[] coords = this.geolocalizacionService.geolocalizar(restaurante.getDireccion());
            restaurante.setLatitud(coords[0]);
            restaurante.setLongitud(coords[1]);
            restaurante.setUrlWeb(restauranteView.getUrlWeb());
            restaurante.setImagenUrl(restauranteView.getImagenUrl());
            restaurante.setUbicacion(restauranteView.getUbicacion());
            restaurante.setCodigoPostal(restauranteView.getCodigoPostal());
            restaurante.setValoracion(restauranteView.getValoracion());
            return RestauranteMapper.entityToDto(this.restauranteRepository.save(restaurante));
        }
        return null;
    }

    @Override
    public RestauranteDTO update(Long id, RestauranteView restauranteView) throws JsonProcessingException {
        Optional<Restaurante> rest = this.restauranteRepository.findById(id);
        if(rest.isPresent()){
            Restaurante restaurante = rest.get();
            if(restauranteView.getTitulo() != null){
                restaurante.setTitulo(restauranteView.getTitulo());
            }
            if(restauranteView.getSubtitulo() != null){
                restaurante.setSubtitulo(restauranteView.getSubtitulo());
            }
            if(restauranteView.getTipoRestaurante() != null){
                restaurante.setTipoRestaurante(restauranteView.getTipoRestaurante());
            }
            if(restauranteView.getDireccion() != null){
                restaurante.setDireccion(restauranteView.getDireccion());
                //geolocalización del restaurante
                double[] coords = this.geolocalizacionService.geolocalizar(restaurante.getDireccion());
                restaurante.setLatitud(coords[0]);
                restaurante.setLongitud(coords[1]);
            }
            if(restauranteView.getCodigoPostal() != restaurante.getCodigoPostal()){
                restaurante.setCodigoPostal(restauranteView.getCodigoPostal());
            }
            if(restauranteView.getDireccion() != null){
                restaurante.setDescripcion(restauranteView.getDescripcion());
            }
            if(restauranteView.getEmail() != null){
                restaurante.setEmail(restauranteView.getEmail());
            }
            if(restauranteView.getUbicacion() != null){
                restaurante.setUbicacion(restauranteView.getUbicacion());
            }
            if(restauranteView.getImagenUrl() != null){
                restaurante.setImagenUrl(restauranteView.getImagenUrl());
            }
            if(restauranteView.getNombre() != null){
                restaurante.setNombre(restauranteView.getNombre());
            }
            if(restaurante.getUrlWeb() != null){
                restaurante.setUrlWeb(restauranteView.getUrlWeb());
            }
            if(restaurante.getValoracion() != restauranteView.getValoracion()){
                restaurante.setValoracion(restauranteView.getValoracion());
            }
            return RestauranteMapper.entityToDto(this.restauranteRepository.save(restaurante));
        }
        return null;
    }

    @Override
    public List<RestauranteDTO> byUbicacionAndTipo(Authentication authentication, String ubicacion, List<TipoRestauranteEnum> tiposRestaurante) {
        List<Long> restaurantes =  this.restauranteRepository.findByUbicacion(ubicacion);
        List<RestauranteDTO> restaurantesDTO =
                restaurantes.stream()
                    .map(idRestaurante -> {
                        return getById(authentication, idRestaurante);
                    }).toList();

        if(tiposRestaurante != null){
            return restaurantesDTO
                .stream()
                .filter(restaurante -> tiposRestaurante.contains(restaurante.getTipoRestaurante()))
                .toList();
        }else{
            return restaurantesDTO;
        }
    }

    @Override
    public void actualizarGeolocalizacion() throws JsonProcessingException {
        List<Restaurante> restaurantes = this.restauranteRepository.findAll();
        for (Restaurante rest : restaurantes) {
            double[] coords = this.geolocalizacionService.geolocalizar(rest.getDireccion());
            rest.setLatitud(coords[0]);
            rest.setLongitud(coords[1]);
            this.restauranteRepository.save(rest);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        if(this.restauranteRepository.findById(id).isPresent()){
            restauranteRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<TagRestauranteDTO> obtenerTagsRestaurantePorId(Long idRestaurante) {
        return TagRestauranteMapper.entityToDtoList(tagRestauranteRepository.findByIdRestaurante(idRestaurante));

    }


    @Override
    public List<RestauranteDTO> buscarRestaurantesPorNombreDeTag(String nombreTag) {
        return RestauranteMapper.entityToDtoList(this.restauranteRepository.buscarRestaurantesPorNombreDeTag(nombreTag.toUpperCase()))
                .stream()
                .peek(this::setValoracionMedia).toList();
    }

    @Override
    public List<RestauranteDTO> buscarRestaurantesPorNombresDeTag(List<String> tags) {
        Set<RestauranteDTO> restaurantes = new HashSet<>();
       for(String tag:tags){
           restaurantes.addAll(new HashSet<>(this.buscarRestaurantesPorNombreDeTag(tag)));
       }
       return restaurantes.stream().toList()
               .stream()
               .peek(this::setValoracionMedia).toList();
    }

    @Override
    public RestauranteDTO update(RestauranteView restauranteView, Long idRestaurante) throws JsonProcessingException {
        Optional<Restaurante> restaurante = this.restauranteRepository.findById(idRestaurante);
        if(restaurante.isPresent()){
            Restaurante nuevoRestaurante = restaurante.get();
            nuevoRestaurante.setNombre(restauranteView.getNombre());
            nuevoRestaurante.setDireccion(restauranteView.getDireccion());
            nuevoRestaurante.setDescripcion(restauranteView.getDescripcion());
            nuevoRestaurante.setUrlWeb(restauranteView.getUrlWeb());
            nuevoRestaurante.setUbicacion(restauranteView.getUbicacion());
            nuevoRestaurante.setTelefono(restauranteView.getTelefono());
            nuevoRestaurante.setEmail(restauranteView.getEmail());
            nuevoRestaurante.setValoracion(restauranteView.getValoracion());

            //geolocalización del restaurante
            double[] coords = this.geolocalizacionService.geolocalizar(nuevoRestaurante.getDireccion());
            nuevoRestaurante.setLatitud(coords[0]);
            nuevoRestaurante.setLongitud(coords[1]);
            return RestauranteMapper.entityToDto(this.restauranteRepository.save(nuevoRestaurante));
        }else{
            return null;
        }
    }

    private List<RestauranteDTO> getFavorits(List<RestauranteDTO> listado, String emailUsuarioLogin){
        Long idUsuarioLogado = this.usuarioService.getIdUsuarioLogado(emailUsuarioLogin);
        List<Long> idRestaurantesFavorits = this.favoritoRepository.getIdFavoritosByTipoReferencia(idUsuarioLogado, ObjetoEnum.RESTAURANTE.toString());
        return listado
                .stream()
                .peek(rest -> {
                    if(idRestaurantesFavorits.contains(rest.getIdRestaurante())){
                        rest.setEsFavoritoUsuario(true);
                    }
                }).toList();
    }
    private RestauranteDTO getFavorit(RestauranteDTO restaurante, String emailUsuarioLogin){
        Long idUsuarioLogado = this.usuarioService.getIdUsuarioLogado(emailUsuarioLogin);
        List<Long> idRestaurantesFavorits = this.favoritoRepository.getIdFavoritosByTipoReferencia(idUsuarioLogado, ObjetoEnum.RESTAURANTE.toString());
        if(idRestaurantesFavorits.contains(restaurante.getIdRestaurante())){
            restaurante.setEsFavoritoUsuario(true);
        }
        return restaurante;
    }

    private void setValoracionMedia(RestauranteDTO restaurante){
        int valor = this.comentarioRepository.getValoracionObjeto(restaurante.getIdRestaurante(), ObjetoEnum.RESTAURANTE.toString());

        restaurante.setValoracionMedia(
                valor
        );
    }


}



