package com.example.Level_up.controller;


import com.example.Level_up.security.jwt.JwtService;
import com.example.Level_up.service.UsuarioService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UsuarioService usuarioService;

    public  AuthController(AuthenticationManager authManager,  JwtService jwtService, UsuarioService usuarioService){
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody Map<String, String> body){
        System.out.println(body);
        String nombre = body.get("nombre");
        String email = body.get("email");
        String password = body.get("password");

        if (nombre == null || email == null || password == null || email.isBlank() || password.isBlank()){
            throw new IllegalArgumentException("Nombre, Email y password son requeridos");
        }
        usuarioService.register(nombre, email,password);
        return Map.of("message", "Usuario registrado correctamente");
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> body){
        String email = body.get("email");
        String password = body.get("password");

        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));

        if (auth.isAuthenticated()){
            String token = jwtService.generateToken(email);
            return Map.of("token", token);
        }
        throw new RuntimeException("Credenciales invalidas");
    }
}
