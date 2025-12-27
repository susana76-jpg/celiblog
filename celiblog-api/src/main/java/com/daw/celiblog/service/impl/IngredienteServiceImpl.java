package com.daw.celiblog.service.impl;

import com.daw.celiblog.db.entity.Ingrediente;
import com.daw.celiblog.db.entity.Receta;
import com.daw.celiblog.db.entity.RecetaIngrediente;
import com.daw.celiblog.db.repository.IngredienteRepository;
import com.daw.celiblog.db.repository.RecetaIngredienteRepository;
import com.daw.celiblog.db.repository.RecetaRepository;
import com.daw.celiblog.dto.IngredienteView;
import com.daw.celiblog.service.IngredienteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredienteServiceImpl implements IngredienteService {
    private final IngredienteRepository ingredienteRepository;
    private final RecetaIngredienteRepository recetaIngredienteRepository;
    private final RecetaRepository recetaRepository;

    public IngredienteServiceImpl(IngredienteRepository ingredienteRepository, RecetaIngredienteRepository recetaIngredienteRepository, RecetaRepository recetaRepository) {
        this.ingredienteRepository = ingredienteRepository;
        this.recetaIngredienteRepository = recetaIngredienteRepository;
        this.recetaRepository = recetaRepository;
    }


    @Override
    public List<IngredienteView> add(Authentication authentication, List<IngredienteView> ingredientesView) {
        return ingredientesView.stream().map(ingr -> {
            Ingrediente ingrediente = new Ingrediente();
            ingrediente.setNombre(ingr.getNombre());
            ingrediente.setUnidad(ingr.getUnidad());
            Ingrediente nuevoIngrediente = this.ingredienteRepository.save(ingrediente);

            if (this.ingredienteRepository.findById(nuevoIngrediente.getId_Ingrediente()).isPresent() &&
                    this.recetaRepository.findById(ingr.getIdReceta()).isPresent()) {
                RecetaIngrediente recetaIngrediente = new RecetaIngrediente();
                recetaIngrediente.setIngrediente(this.ingredienteRepository.findById(nuevoIngrediente.getId_Ingrediente()).get());
                recetaIngrediente.setCantidad(ingr.getCantidad());
                recetaIngrediente.setReceta(this.recetaRepository.findById(ingr.getIdReceta()).get());
                this.recetaIngredienteRepository.save(recetaIngrediente);
            }
            return ingr;
        }).toList();

    }

    @Override
    public IngredienteView update(Authentication authentication, IngredienteView ingredienteView, Long idIngrediente) {
        Optional<Ingrediente> ingrediente = this.ingredienteRepository.findById(idIngrediente);
        Optional<RecetaIngrediente> recetaIngrediente = this.recetaIngredienteRepository.getByIdRecetaAndIdIngrediente(ingredienteView.getIdReceta(), idIngrediente);
        if (ingrediente.isPresent() && recetaIngrediente.isPresent()) {
            Ingrediente ingredienteUpdate = ingrediente.get();
            if (ingredienteView.getCantidad() != recetaIngrediente.get().getCantidad()) {
                RecetaIngrediente recetaIngredienteUpdate = recetaIngrediente.get();
                recetaIngredienteUpdate.setCantidad(ingredienteView.getCantidad());
                this.recetaIngredienteRepository.save(recetaIngredienteUpdate);
            }
            if (ingredienteView.getNombre() != null) {
                ingredienteUpdate.setNombre(ingredienteView.getNombre());
            }
            if (ingredienteView.getUnidad() != null) {
                ingredienteUpdate.setUnidad(ingredienteView.getUnidad());
            }
            this.ingredienteRepository.save(ingredienteUpdate);
            return ingredienteView;
        }
        return null;
    }

    @Override
    public boolean deleteById(Long idReceta, Long idIngrediente) {
        Optional<Ingrediente> ingrediente = this.ingredienteRepository.findById(idIngrediente);
        Optional<RecetaIngrediente> recetaIngrediente = this.recetaIngredienteRepository.getByIdRecetaAndIdIngrediente(idReceta, idIngrediente);
        if (ingrediente.isPresent() && recetaIngrediente.isPresent()) {
            this.recetaIngredienteRepository.deleteById(recetaIngrediente.get().getId_recetaIngrediente());
            this.ingredienteRepository.deleteById(idIngrediente);
            return true;
        }
        return false;
    }

    @Override
    public List<IngredienteView> updateAll(Authentication authentication, List<IngredienteView> ingredientes, Receta receta) {
        List<Ingrediente> ingredientesAntiguos = this.ingredienteRepository.findByIdReceta(receta.getIdReceta());

            //se eliminan los ingredientes antiguos de la receta, si los tuviese
            if(ingredientesAntiguos.isEmpty()){
                ingredientesAntiguos.forEach(ingrediente -> {
                    Optional<RecetaIngrediente> recetaIngrediente = this.recetaIngredienteRepository.getByIdRecetaAndIdIngrediente(receta.getIdReceta(), ingrediente.getId_Ingrediente());
                    recetaIngrediente.ifPresent(value -> this.deleteById(receta.getIdReceta(), value.getIngrediente().getId_Ingrediente()));
                });
            }

            //se dan de alta los nuevos ingredientes y su objeto recetaIngrediente de cada uno
            if (!ingredientes.isEmpty()) {
                return ingredientes.stream().map(ingredient -> {
                    Ingrediente nuevoIngrediente = new Ingrediente();
                    nuevoIngrediente.setNombre(ingredient.getNombre());
                    nuevoIngrediente.setUnidad(ingredient.getUnidad());
                    Ingrediente ing = this.ingredienteRepository.save(nuevoIngrediente);
                    RecetaIngrediente recetaIngrediente = new RecetaIngrediente();
                    if (ing != null) {
                        recetaIngrediente.setCantidad(ingredient.getCantidad());
                        recetaIngrediente.setIngrediente(ing);
                        recetaIngrediente.setReceta(receta);
                        this.recetaIngredienteRepository.save(recetaIngrediente);
                    }
                    return new IngredienteView(receta.getIdReceta(), nuevoIngrediente.getNombre(), nuevoIngrediente.getUnidad(), recetaIngrediente.getCantidad());
                }).toList();
            }


            return null;

    }

}
