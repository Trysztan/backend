package com.gym.gymapp.services.recipe;

import com.gym.gymapp.model.User;
import com.gym.gymapp.model.exerciselist.Exercise;
import com.gym.gymapp.model.exerciselist.ExerciseList;
import com.gym.gymapp.model.recipe.Ingredient;
import com.gym.gymapp.model.recipe.NutritionFacts;
import com.gym.gymapp.model.recipe.Recipe;
import com.gym.gymapp.repositories.*;
import com.gym.gymapp.services.ingredient.IngredientService;
import com.gym.gymapp.services.nutrition_facts.NutritionFactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class RecipeImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final IngredientService ingredientService;
    private final NutritionFactsService nutritionFactsService;
    private final UserRepository userRepository;

    @Autowired
    public RecipeImpl(
            RecipeRepository recipeRepository,
            UserRepository userRepository,
            IngredientService ingredientService,
            NutritionFactsService nutritionFactsService
    ) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
        this.ingredientService = ingredientService;
        this.nutritionFactsService = nutritionFactsService;
    }

    @Override
    public Optional<Recipe> getById(Long id) {
        return recipeRepository.getById(id);
    }

    @Override
    public Optional<Recipe> creatRecipe(Recipe newrecipe, Long userid) {
        if (recipeRepository.existsById(newrecipe.getId())) {
            return Optional.empty();
        }

        Recipe recipe = new Recipe();
        recipe.setId(newrecipe.getId());
        recipe.setName(newrecipe.getName());
        recipe.setServing(newrecipe.getServing());

        User user = userRepository.findById(userid).orElse(null);
        if (user != null) {
            recipe.setCreator(user);
            recipeRepository.save(recipe);

            List<Ingredient> ingredientList = newrecipe.getIngredients();
            NutritionFacts nutritionFacts = newrecipe.getNutritionFacts();
            nutritionFactsService.createNutritionFacts(nutritionFacts, recipe.getId());
            for (Ingredient ingredient : ingredientList) {
                ingredientService.createIngredient(ingredient, recipe.getId());
            }
            recipe.setIngredients(newrecipe.getIngredients());
            recipe.setNutritionFacts(newrecipe.getNutritionFacts());
            recipeRepository.save(recipe);

            return Optional.of(recipe);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Recipe> getMyAllRecipe(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            return recipeRepository.findByCreator(user);
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<Recipe> uploadRecipePic(Long id, MultipartFile file){

        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if(recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();
            try {
                recipe.setImage(file.getBytes());
                recipeRepository.save(recipe);

            } catch (IOException e) {
                throw new RuntimeException("Hiba a kép feltöltése közben");
            }
            return Optional.of(recipe);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public byte[] getRecipePic(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        return recipe.map(Recipe::getImage).orElse(null);
    }

    @Override
    public Optional<Recipe> updateRecipe(Recipe newrecipe, Long userid, MultipartFile file) {
        User user = userRepository.findById(userid).orElse(null);
        Optional<Recipe> oldRecipe = recipeRepository.findById(newrecipe.getId());

        if (oldRecipe.isPresent() && user!=null) {
            Recipe updatedRecipe = oldRecipe.get();
            if (newrecipe.getIngredients().isEmpty()) {
                deleteRecipe(updatedRecipe.getId());
                return Optional.empty();
            }
            updatedRecipe.setName(newrecipe.getName());
            updatedRecipe.setServing(newrecipe.getServing());
            updatedRecipe.setDirections(newrecipe.getDirections());
            updatedRecipe.setIngredients(newrecipe.getIngredients());
            updatedRecipe.setNutritionFacts(newrecipe.getNutritionFacts());
            recipeRepository.save(updatedRecipe);
            return Optional.of(updatedRecipe);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteRecipe(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if (recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();

            recipeRepository.delete(recipe);
        } else {
            System.out.println("Hiba történt a recept törlése közben");
        }
    }

}


