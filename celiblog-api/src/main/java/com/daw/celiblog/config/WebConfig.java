package com.daw.celiblog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")   // Permitir todos los orígenes
                .allowedMethods("*")   // Permitir todos los métodos (GET, POST, PUT, DELETE, etc.)
                .allowedHeaders("*")    // Permitir todos los headers
                .allowedOrigins("http://localhost:3000") // origen del local
                .allowedOrigins("http://46.183.113.124:3000") //orignen servidor
                .allowedMethods("GET","POST","PUT","DELETE")
                .allowCredentials(true)
                .exposedHeaders("Authorization");

    }

}
