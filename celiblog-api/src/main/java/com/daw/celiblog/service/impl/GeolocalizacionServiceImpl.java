package com.daw.celiblog.service.impl;

import com.daw.celiblog.service.GeolocalizacionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeolocalizacionServiceImpl implements GeolocalizacionService {
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${opencage.api.key}")
    private String apiKey;


    @Override
    public double[] geolocalizar(String direccion) throws JsonProcessingException {
        String url = String.format(
                "https://api.opencagedata.com/geocode/v1/json?q=%s&key=%s",
                direccion, apiKey
        );

        String response = restTemplate.getForObject(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response);

        JsonNode geometry = root.path("results").get(0).path("geometry");
        double lat = geometry.path("lat").asDouble();
        double lng = geometry.path("lng").asDouble();

        return new double[]{lat, lng};
    }

    public String reverseGeolocalizar(double latitud, double longitud) throws JsonProcessingException {
        String url = String.format(
                "https://api.opencagedata.com/geocode/v1/json?q=%f+%f&key=%s",
                latitud, longitud, apiKey
        );

        String response = restTemplate.getForObject(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response);

        // Extraer la dirección formateada
        String direccion = root.path("results").get(0).path("formatted").asText();
        return direccion;
    }

}

