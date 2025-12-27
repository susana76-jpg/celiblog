package com.daw.celiblog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")   // Permitir todos los métodos (GET, POST, PUT, DELETE, etc.)
                .allowedHeaders("*")    // Permitir todos los headers
                .allowedOrigins("http://localhost", "http://localhost:3000",
                        "http://46.183.113.124",  "https://46.183.113.124",
                        "http://46.183.113.124:8081", "https://46.183.113.124:8081",
                        "http://46.183.113.124:3000", "https://46.183.113.124:3000",
                        "http://celiblog.es", "https://celiblog.es",
                        "http://www.celiblog.es", "https://www.celiblog.es",
                        "http://api.celiblog.es", "https://api.celiblog.es",
                        "http://api.celiblog.es:8081", "https://api.celiblog.es:8081")
                .allowedMethods("GET","POST","PUT","DELETE")
                .allowCredentials(true)
                .exposedHeaders("Authorization");

    }

}
