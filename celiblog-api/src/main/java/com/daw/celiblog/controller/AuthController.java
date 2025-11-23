package com.daw.celiblog.controller;

import com.daw.celiblog.dto.LoginRequestDTO;
import com.daw.celiblog.dto.LoginResponseDTO;
import com.daw.celiblog.service.JwtService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
        // Aquí validas usuario/contraseña contra tu base de datos
        if ("admin".equals(request.getUsername()) && "1234".equals(request.getPassword())) {
            //String token = jwtService.generateToken(request.getUsername());
            //return ResponseEntity.ok(new LoginResponseDTO(token));
            return null;
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
