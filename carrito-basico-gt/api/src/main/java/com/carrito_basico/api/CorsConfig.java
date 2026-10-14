package com.carrito_basico.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Aplica a todas las rutas de la API
                        .allowedOrigins("http://localhost:4200") // para permitir a Angular
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // permite estos metodos
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
