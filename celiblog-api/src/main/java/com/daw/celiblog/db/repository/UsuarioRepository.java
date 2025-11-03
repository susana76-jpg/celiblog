package com.daw.celiblog.db.repository;

import com.daw.celiblog.db.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
