package com.empresa.gestion_empresa;

import com.empresa.gestion_empresa.model.Rol;
import com.empresa.gestion_empresa.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public void run(String... args) throws Exception {
        if (rolRepository.findByNombre("ROLE_USER").isEmpty()) {
            Rol rolUser = new Rol();
            rolUser.setNombre("ROLE_USER");
            rolRepository.save(rolUser);
        }

        if (rolRepository.findByNombre("ROLE_ADMIN").isEmpty()) {
            Rol rolAdmin = new Rol();
            rolAdmin.setNombre("ROLE_ADMIN");
            rolRepository.save(rolAdmin);
        }
    }
}
