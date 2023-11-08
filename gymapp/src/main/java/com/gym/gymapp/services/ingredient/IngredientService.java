package com.gym.gymapp.services.ingredient;

import com.gym.gymapp.model.recipe.Ingredient;

import java.util.Optional;

public interface IngredientService {
    Optional<Ingredient> createIngredient(Ingredient ingredient, Long userid);
    Optional<Ingredient> getIngredient(Long id);

    Optional<Ingredient> updateIngredient(Ingredient ingredient);

    void deleteIngredient(Long id);

}

