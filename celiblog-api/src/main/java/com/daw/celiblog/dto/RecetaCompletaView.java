package com.daw.celiblog.dto;

import com.daw.celiblog.enums.TipoComidaEnum;

import java.util.List;

public class RecetaCompletaView {
   private RecetaView recetaView;
    private List<IngredienteView> ingredientes;
    private List<PasoRecetaView> pasos;

    public RecetaCompletaView() {
    }

    public RecetaCompletaView(RecetaView recetaView, List<IngredienteView> ingredientes, List<PasoRecetaView> pasos) {
        this.recetaView = recetaView;
        this.ingredientes = ingredientes;
        this.pasos = pasos;
    }

    public RecetaView getRecetaView() {
        return recetaView;
    }

    public void setRecetaView(RecetaView recetaView) {
        this.recetaView = recetaView;
    }

    public List<IngredienteView> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<IngredienteView> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public List<PasoRecetaView> getPasos() {
        return pasos;
    }

    public void setPasos(List<PasoRecetaView> pasos) {
        this.pasos = pasos;
    }
}
