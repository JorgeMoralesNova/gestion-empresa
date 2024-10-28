package com.empresa.gestion_empresa.service;

import com.empresa.gestion_empresa.model.Usuario;
import com.empresa.gestion_empresa.model.Rol;
import com.empresa.gestion_empresa.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Método para registrar un usuario nuevo
    public void registrarUsuario(Usuario usuario, Rol rol) {
        // Encriptar la contraseña antes de guardar
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.getRoles().add(rol);
        usuarioRepository.save(usuario);
    }
}
