package com.daw.celiblog.controller;

import com.daw.celiblog.db.entity.Rol;
import com.daw.celiblog.db.entity.Usuario;
import com.daw.celiblog.db.repository.RolRepository;
import com.daw.celiblog.db.repository.UsuarioRepository;
import com.daw.celiblog.dto.AuthRequest;
import com.daw.celiblog.dto.AuthResponse;
import com.daw.celiblog.enums.RolEnum;
import com.daw.celiblog.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRepository rolRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        System.out.println("Email recibido: " + request.getEmail());
        System.out.println("Password recibido: " + request.getPassword());

        if (request.getEmail()==null || request.getPassword()==null){
            return ResponseEntity.badRequest().body("Bad petition");
        }else if(this.usuarioRepository.findByEmail(request.getEmail()).isEmpty()){
            return ResponseEntity.badRequest().body("No existe el email con el que estás intentando hacer login.");
        }else{
            Authentication authenticationRequest =
                    UsernamePasswordAuthenticationToken.unauthenticated(request.getEmail(), request.getPassword());
            Authentication authenticationResponse =
                    this.authenticationManager.authenticate(authenticationRequest);

            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
            String accessToken = jwtUtil.generateToken(userDetails);
            String refreshToken = jwtUtil.generateRefreshToken(userDetails);

            return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
        }
    }

    //Registro con BCrypt
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {
        if(request.getEmail() != null && request.getEmail() != "" && this.usuarioRepository.findByEmail(request.getEmail()).isPresent()){
            return ResponseEntity.ok("Email ya existente o mal conformado");
        }else{
            Usuario user = new Usuario();
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword())); // encriptación con BCrypt
            user.setRol(this.rolRepository.findById(3L).get());
            user.setNombre(request.getNombre());
            usuarioRepository.save(user);

            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
            String accessToken = jwtUtil.generateToken(userDetails);
            String refreshToken = jwtUtil.generateRefreshToken(userDetails);

            return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
        }


    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        String email = jwtUtil.extractUsername(refreshToken);

        Usuario user = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        if (jwtUtil.validateToken(refreshToken, userDetails)) {
            String newAccessToken = jwtUtil.generateToken(userDetails);
            return ResponseEntity.ok(new AuthResponse(refreshToken,refreshToken));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}

