package com.ecommerce.ecommerceapi.config; // O pacote que acabamos de criar

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Desabilita o CSRF, que não é tão necessário para uma API REST stateless
                .csrf(AbstractHttpConfigurer::disable)
                // Autoriza todas as requisições HTTP
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll() // Permite acesso a qualquer URL
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}