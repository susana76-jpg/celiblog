package com.daw.celiblog.service.impl;

import com.daw.celiblog.db.entity.Rol;
import com.daw.celiblog.db.entity.Usuario;
import com.daw.celiblog.db.repository.RolRepository;
import com.daw.celiblog.db.repository.UsuarioRepository;
import com.daw.celiblog.dto.UsuarioDTO;
import com.daw.celiblog.dto.UsuarioView;
import com.daw.celiblog.service.UsuarioService;
import com.daw.celiblog.service.mapper.RestauranteMapper;
import com.daw.celiblog.service.mapper.RolMapper;
import com.daw.celiblog.service.mapper.UsuarioMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    @Override
    public UsuarioDTO obtenerUsuarioPorId(Long idUsuario) {
         return this.usuarioRepository.findById(idUsuario)
                .map(UsuarioMapper::entityToDto)
                .orElse(null);
    }

    @Override
    public List<UsuarioDTO> obtenerTodos() {
        return UsuarioMapper.entityToDtoList(this.usuarioRepository.findAll());
    }

    @Override
    public UsuarioDTO obtenerPorId(Long id) {
        return this.usuarioRepository.findById(id)
                .map(UsuarioMapper::entityToDto)
                .orElse(null);
    }

    @Override
    public UsuarioDTO crear(UsuarioView view) {
        Optional<Rol> rol = rolRepository.findById(view.getIdRol());
        if(rol.isPresent()){
            Usuario nuevoUsuario = new Usuario(view.getEmail(), view.getNombre(), view.getPassword(), rol.get());
            return UsuarioMapper.entityToDto(this.usuarioRepository.save(nuevoUsuario));
        }
       return null;
    }

    @Override
    public UsuarioDTO actualizarRol(Long idUsuario, Long idNuevoRol) {
        Optional<Usuario> usu = this.usuarioRepository.findById(idUsuario);
        Optional<Rol> nuevoRol = this.rolRepository.findById(idNuevoRol);
        if(usu.isPresent() && nuevoRol.isPresent()){
            Usuario usuario = usu.get();
            usuario.setRol(nuevoRol.get());
            return UsuarioMapper.entityToDto(this.usuarioRepository.save(usuario));
            }
        return null;
    }

    @Override
    public boolean eliminar(Long id) {
        if(this.usuarioRepository.findById(id).isPresent()){
            this.usuarioRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
