package com.empresa.gestion_empresa;

import com.empresa.gestion_empresa.model.Rol;
import com.empresa.gestion_empresa.model.Usuario;
import com.empresa.gestion_empresa.repository.RolRepository;
import com.empresa.gestion_empresa.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        // Crear ROLE_USER si no existe
        if (rolRepository.findByNombre("ROLE_USER").isEmpty()) {
            Rol rolUser = new Rol();
            rolUser.setNombre("ROLE_USER");
            rolRepository.save(rolUser);
        }

        // Crear ROLE_ADMIN si no existe
        if (rolRepository.findByNombre("ROLE_ADMIN").isEmpty()) {
            Rol rolAdmin = new Rol();
            rolAdmin.setNombre("ROLE_ADMIN");
            rolRepository.save(rolAdmin);
        }

        // Crear un usuario admin si no existe
        if (usuarioRepository.findByUsername("admin").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword(new BCryptPasswordEncoder().encode("admin123"));
            // Asignar el rol ADMIN
            Rol rolAdmin = rolRepository.findByNombre("ROLE_ADMIN").orElseThrow(() -> new RuntimeException("ROLE_ADMIN no encontrado"));
            admin.setRoles(Set.of(rolAdmin));
            usuarioRepository.save(admin);
        }
    }
}
