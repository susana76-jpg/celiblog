package com.daw.celiblog.service.impl;

import com.daw.celiblog.db.entity.Restaurante;
import com.daw.celiblog.db.entity.Usuario;
import com.daw.celiblog.db.repository.FavoritoRepository;
import com.daw.celiblog.db.repository.RestauranteRepository;
import com.daw.celiblog.db.repository.TagRestauranteRepository;
import com.daw.celiblog.db.repository.UsuarioRepository;
import com.daw.celiblog.dto.*;
import com.daw.celiblog.enums.EstadoValidacionEnum;
import com.daw.celiblog.enums.ObjetoEnum;
import com.daw.celiblog.service.GeolocalizacionService;
import com.daw.celiblog.service.RestauranteService;
import com.daw.celiblog.service.UsuarioService;
import com.daw.celiblog.service.mapper.RestauranteMapper;
import com.daw.celiblog.service.mapper.TagRestauranteMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestauranteServiceImpl implements RestauranteService {

    private final RestauranteRepository restauranteRepository;

    private final TagRestauranteRepository tagRestauranteRepository;
    private final UsuarioRepository usuarioRepository;
    private final GeolocalizacionService geolocalizacionService;
    private final UsuarioService usuarioService;
    private final FavoritoRepository favoritoRepository;



    public RestauranteServiceImpl(RestauranteRepository restauranteRepository, TagRestauranteRepository tagRestauranteRepository, UsuarioRepository usuarioRepository, GeolocalizacionService geolocalizacionService, UsuarioService usuarioService, FavoritoRepository favoritoRepository) {
        this.restauranteRepository = restauranteRepository;
        this.tagRestauranteRepository = tagRestauranteRepository;
        this.usuarioRepository = usuarioRepository;
        this.geolocalizacionService = geolocalizacionService;
        this.usuarioService = usuarioService;
        this.favoritoRepository = favoritoRepository;
    }


    @Override
    public List<RestauranteDTO> obtenerTodos() {
        return restauranteRepository.findAll().stream()
                .map(RestauranteMapper::entityToDto)
                .collect(Collectors.toList());
   }

    @Override
    public RestauranteDTO obtenerPorId(Long id) {
        return restauranteRepository
                .findById(id)
                .map(RestauranteMapper::entityToDto)
                .orElse(null);
    }

    @Override
    public RestauranteDTO crear(RestauranteDTO dto) {
        return RestauranteMapper.entityToDto(this.restauranteRepository.save(RestauranteMapper.dtoToEntity(dto)));
    }

    @Override
    public RestauranteDTO actualizar(Long id, RestauranteDTO dto) throws JsonProcessingException {
        Optional<Restaurante> rest = this.restauranteRepository.findById(id);
        if(rest.isPresent()){
            Restaurante restaurante = rest.get();
            restaurante.setDireccion(dto.getDireccion());
            restaurante.setCodigoPostal(rest.get().getCodigoPostal());
            restaurante.setDescripcion(dto.getDescripcion());
            restaurante.setUbicacion(dto.getUbicacion());
            restaurante.setImagenUrl(dto.getImagenUrl());
            restaurante.setNombre(dto.getNombre());
            restaurante.setUrlWeb(dto.getUrlWeb());
            //geolocalización del restaurante
            double[] coords = this.geolocalizacionService.geolocalizar(dto.getDireccion());
            restaurante.setLatitud(coords[0]);
            restaurante.setLongitud(coords[1]);
            return RestauranteMapper.entityToDto(this.restauranteRepository.save(restaurante));
        }
        return null;

    }

    @Override
    public List<RestauranteDTO> byUbicacion(String ubicacion) {
        return RestauranteMapper.entityToDtoList(this.restauranteRepository.findByUbicacion(ubicacion)) ;
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
    public boolean eliminar(Long id) {
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
        return RestauranteMapper.entityToDtoList(this.restauranteRepository.buscarRestaurantesPorNombreDeTag(nombreTag.toUpperCase()));
    }

    @Override
    public List<RestauranteDTO> buscarRestaurantesPorNombresDeTag(List<String> tags) {
        Set<RestauranteDTO> restaurantes = new HashSet<>();
       for(String tag:tags){
           restaurantes.addAll(new HashSet<>(this.buscarRestaurantesPorNombreDeTag(tag)));
       }
       return restaurantes.stream().toList();
    }


    @Override
    public RestauranteDTO crearRestaurante(RestauranteView restauranteView) throws JsonProcessingException {
        Optional<Usuario> usuario = this.usuarioRepository.findById(restauranteView.getIdUsuario());
        if(usuario.isPresent()){
            Restaurante nuevoRestaurante = new Restaurante();
            nuevoRestaurante.setNombre(restauranteView.getNombre());
            nuevoRestaurante.setDireccion(restauranteView.getDireccion());
            nuevoRestaurante.setCodigoPostal(restauranteView.getCodigoPostal());
            nuevoRestaurante.setTitulo(restauranteView.getTitulo());
            nuevoRestaurante.setSubtitulo(restauranteView.getSubtitulo());
            nuevoRestaurante.setDescripcion(restauranteView.getDescripcion());
            nuevoRestaurante.setUrlWeb(restauranteView.getUrlWeb());
            nuevoRestaurante.setImagenUrl(restauranteView.getImagen_url());
            nuevoRestaurante.setUbicacion(restauranteView.getUbicacion());
            nuevoRestaurante.setTelefono(restauranteView.getTelefono());
            nuevoRestaurante.setEmail(restauranteView.getEmail());
            nuevoRestaurante.setTelefono(restauranteView.getTelefono());
            nuevoRestaurante.setUsuario(usuario.get());
            nuevoRestaurante.setValoracion(restauranteView.getValoracion());

            //geolocalización del restaurante
            double[] coords = this.geolocalizacionService.geolocalizar(nuevoRestaurante.getDireccion());
            nuevoRestaurante.setLatitud(coords[0]);
            nuevoRestaurante.setLongitud(coords[1]);

            return RestauranteMapper.entityToDto(this.restauranteRepository.save(nuevoRestaurante));
        }
        return null;
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

    @Override
    public List<RestauranteDTO> getRestaurantesEstadoPendiente() {
        return RestauranteMapper.entityToDtoList(this.restauranteRepository.getRestaurantesEstadoPendiente());
    }

    @Override
    public List<RestauranteDTO> getRestaurantesEstadoAprobado() {
        return RestauranteMapper.entityToDtoList(this.restauranteRepository.getRestaurantesEstadoAprobado());
    }

    @Override
    public List<RestauranteDTO> getRestaurantesEstadoRechazado() {
        return RestauranteMapper.entityToDtoList(this.restauranteRepository.getRestaurantesEstadoRechazado());
    }

    @Override
    public RestauranteDTO updateEstadoPublicacionRestaurante(Long idRestaurante, EstadoValidacionEnum estado) {
        Optional<Restaurante> rest = this.restauranteRepository.findById(idRestaurante);
        if(rest.isPresent()){
            Restaurante restaurante = rest.get();
            restaurante.setEstado(estado);
            return RestauranteMapper.entityToDto(this.restauranteRepository.save(restaurante));
        }
        return null;
    }

    @Override
    public List<RestauranteDTO> obtenerTodosConFavoritosUsuario(String emailUsuario) {
        Long idUsuarioLogado = this.usuarioService.getIdUsuarioLogado(emailUsuario);
        List<Long> idRestaurantesFavoritas = this.favoritoRepository.getIdFavoritosByTipoReferencia(idUsuarioLogado, ObjetoEnum.RESTAURANTE);
        return obtenerTodos()
                .stream()
                .map(rest -> {
                    if(idRestaurantesFavoritas.contains(rest.getIdRestaurante())){
                        rest.setEsFavoritoUsuario(true);
                    }
                    return rest;
                }).toList();
    }

    @Override
    public List<RestauranteDTO> obtenerRestaurantesFavoritosUsuario(String emailUsuario) {
        Long idUsuarioLogado = this.usuarioService.getIdUsuarioLogado(emailUsuario);
        List<Long> idRestaurantesFavoritos = this.favoritoRepository.getIdFavoritosByTipoReferencia(idUsuarioLogado, ObjetoEnum.RESTAURANTE);

        return idRestaurantesFavoritos
                .stream()
                .map(this::obtenerPorId)
                .toList();
    }


}



