package com.daw.celiblog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Profile("!prod")
public class WebConfigDev implements WebMvcConfigurer {
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")    // Permitir todos los headers
                .allowedOrigins("http://localhost", "http://localhost:8081")
                .allowedMethods("GET","POST","PUT","DELETE")
                .allowCredentials(true)
                .exposedHeaders("Authorization");

    }

}
