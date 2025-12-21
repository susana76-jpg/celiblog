package com.daw.celiblog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Profile("dev")
public class WebConfigDev implements WebMvcConfigurer {
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")   // Permitir todos los orígenes
                .allowedMethods("*")   // Permitir todos los métodos (GET, POST, PUT, DELETE, etc.)
                .allowedHeaders("*")    // Permitir todos los headers
                .allowedMethods("GET","POST","PUT","DELETE")
                .allowCredentials(false)
                .exposedHeaders("Authorization");

    }

}
