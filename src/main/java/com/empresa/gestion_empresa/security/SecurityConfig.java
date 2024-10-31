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
                        // Permitir el acceso a /registro, /login, /menu y recursos estáticos sin autenticación
                        .requestMatchers("/login", "/resources/**").permitAll()
                        .anyRequest().authenticated() // Todas las demás rutas requieren autenticación
                )
                .formLogin((form) -> form
                        .loginPage("/login")  // Página personalizada de login
                        .defaultSuccessUrl("/home", true)  // Redirigir a /menu después de un login exitoso
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")  // URL para cerrar sesión
                        .logoutSuccessUrl("/login?logout")  // Redirige al login después de logout
                        .permitAll()
                );

        return http.build();
    }
}
