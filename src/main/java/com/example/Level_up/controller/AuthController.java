package com.example.Level_up.controller;


import com.example.Level_up.security.jwt.JwtService;
import com.example.Level_up.service.UsuarioService;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> register(@RequestBody Map<String, String> body){
        try {
            String nombre = body.get("nombre");
            String email = body.get("email");
            String password = body.get("password");

            if (nombre == null || email == null || password == null || email.isBlank() || password.isBlank()) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "Email y password son requeridos"));
            }
            usuarioService.register(nombre, email, password);
            return ResponseEntity.ok()
                    .body(Map.of("message", "Usuario registrado correctamente"));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error" , "El usuario ya existe"));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body){
        try {
            String email = body.get("email");
            String password = body.get("password");

            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password));

            if (auth.isAuthenticated()) {
                String token = jwtService.generateToken(email);
                return ResponseEntity.ok(Map.of(
                        "token", token,
                        "email", email
                ));
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Credenciales invalidas"));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error" , "Email o contraseña incorrectos"));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error" , "Error al procesar la solicitud"));
        }
    }
    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(){
        return ResponseEntity.ok(Map.of("valid" , true));
    }
}
