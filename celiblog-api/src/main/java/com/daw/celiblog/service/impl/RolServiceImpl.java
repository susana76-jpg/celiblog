package com.daw.celiblog.service.impl;

import com.daw.celiblog.db.entity.Rol;
import com.daw.celiblog.db.repository.RolRepository;
import com.daw.celiblog.service.RolService;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl implements RolService {

    public final RolRepository rolRepository;

    public RolServiceImpl(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public Rol getRolById(Long id) {
        return this.rolRepository.findById(id).get();
    }
}
