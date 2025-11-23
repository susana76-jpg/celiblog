package com.daw.celiblog.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface GeolocalizacionService {
    public double[] geolocalizar(String direccion) throws JsonProcessingException;
    public String reverseGeolocalizar(double latitud, double longitud) throws JsonProcessingException;
}
