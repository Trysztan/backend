package com.gym.gymapp.services.ingredient;

import com.gym.gymapp.model.recipe.Ingredient;
import com.gym.gymapp.model.recipe.Recipe;
import com.gym.gymapp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
@Service
public class IngredientImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;


    @Autowired
    public IngredientImpl(
            IngredientRepository ingredientRepository,
            UserRepository userRepository,
            RecipeRepository recipeRepository
    ) {
        this.ingredientRepository = ingredientRepository;
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;

    }

    @Override
    public Optional<Ingredient> createIngredient(Ingredient newingredient, Long recipeid) {
        if (ingredientRepository.existsById(newingredient.getId())) {
            return Optional.empty();
        }
        Ingredient ingredient = new Ingredient();
        ingredient.setId(newingredient.getId());
        ingredient.setQuantity(newingredient.getQuantity());
        ingredient.setName(newingredient.getName());
        ingredient.setUnit_of_measure(newingredient.getUnit_of_measure());
        ingredient.setDescription(newingredient.getDescription());
        Recipe recipe = recipeRepository.findById(recipeid).orElse(null);

        if (recipe != null) {
            ingredient.setRecipe(recipe);
            return Optional.of(ingredientRepository.save(ingredient));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Ingredient> getIngredient(Long id) {
        return ingredientRepository.findById(id);
    }

    @Override
    public Optional<Ingredient> updateIngredient(Ingredient newingredient) {
        Optional<Ingredient> existingIngredient = ingredientRepository.findById(newingredient.getId());

        if (existingIngredient.isPresent()) {
            Ingredient ingredientToUpdate = existingIngredient.get();
            ingredientToUpdate.setName(newingredient.getName());
            ingredientToUpdate.setQuantity(newingredient.getQuantity());
            ingredientToUpdate.setUnit_of_measure(newingredient.getUnit_of_measure());
            ingredientToUpdate.setDescription(newingredient.getDescription());
            Recipe recipe = recipeRepository.findById(ingredientToUpdate.getRecipe().getId()).orElse(null);

            if (recipe != null) {
                ingredientToUpdate.setRecipe(recipe);
                return Optional.of(ingredientRepository.save(ingredientToUpdate));
            } else {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    @Override
    public void deleteIngredient(Long id) {
        Optional<Ingredient> exercise = ingredientRepository.findById(id);
        exercise.ifPresent(ingredientRepository::delete);
    }
}

