package com.empresa.gestion_empresa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.empresa.gestion_empresa.security.SecurityConfig;

@SpringBootApplication

@Import(SecurityConfig.class)
public class GestionEmpresaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionEmpresaApplication.class, args);
	}

}
