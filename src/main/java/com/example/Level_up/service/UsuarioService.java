package com.example.Level_up.service;


import com.example.Level_up.model.Usuario;
import com.example.Level_up.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario register(String nombre, String email ,String password, String role){
        Usuario usuario = Usuario.builder()
                .nombre(nombre)
                .password(passwordEncoder.encode(password))
                .email(email)
                .role(role != null ? role : "USER")
                .build();
        return usuarioRepository.save(usuario);
    }

    public Usuario register(String nombre, String email, String password){
        return register(nombre, email, password, "USER");
    }

    public Optional<Usuario> findByEmail(String email){
        return usuarioRepository.findByEmail(email);
    }
}
