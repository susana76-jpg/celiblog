package com.daw.celiblog.dto;

import com.daw.celiblog.enums.EstadoValidacionEnum;
import com.daw.celiblog.enums.TipoComidaEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.Objects;

public class RecetaView {

    @NotNull(message = "Este campo no debe ser nulo")
    private String descripcion;
    private String imagenUrl;
    @NotNull(message = "Este campo no debe ser nulo")
    private String dificultad;
    @NotNull(message = "Este campo no debe ser nulo")
    private String titulo;
    @NotNull(message = "Este campo no debe ser nulo")
    private String subtitulo;
    @NotNull(message = "Este campo no debe ser nulo")
    private int valoracion;
    @NotNull(message = "Este campo no debe ser nulo")
    private int comensales;
    @NotNull(message = "Este campo no debe ser nulo")
    private int tiempoPreparacion;
    @NotNull(message = "Este campo no debe ser nulo")
    private int valorEnergetico;
    @NotNull(message = "Este campo no debe ser nulo")
    private TipoComidaEnum tipoComida;


    public RecetaView() {
    }

    public RecetaView(String descripcion, String imagenUrl, String dificultad, String titulo, String subtitulo, int valoracion, int comensales, int tiempoPreparacion, int valorEnergetico, TipoComidaEnum tipoComida) {
        this.descripcion = descripcion;
        this.imagenUrl = imagenUrl;
        this.dificultad = dificultad;
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.valoracion = valoracion;
        this.comensales = comensales;
        this.tiempoPreparacion = tiempoPreparacion;
        this.valorEnergetico = valorEnergetico;
        this.tipoComida = tipoComida;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public int getComensales() {
        return comensales;
    }

    public void setComensales(int comensales) {
        this.comensales = comensales;
    }

    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(int tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }

    public int getValorEnergetico() {
        return valorEnergetico;
    }

    public void setValorEnergetico(int valorEnergetico) {
        this.valorEnergetico = valorEnergetico;
    }

    public TipoComidaEnum getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(TipoComidaEnum tipoComida) {
        this.tipoComida = tipoComida;
    }
}
