package com.empresa.gestion_empresa.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        // Permitir el acceso a /registro, /login y recursos estáticos sin autenticación
                        .requestMatchers("/login", "/resources/**").permitAll()
                        .anyRequest().authenticated() // Todas las demás rutas requieren autenticación
                )
                .formLogin((form) -> form
                        .loginPage("/login")  // Página personalizada de login
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")  // Redirige a /logout cuando se cierra sesión
                        .logoutSuccessUrl("/login?logout")  // Redirige al login después de logout
                        .permitAll()
                );

        return http.build();
    }

}
