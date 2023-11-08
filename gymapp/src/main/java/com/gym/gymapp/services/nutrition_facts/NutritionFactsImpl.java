package com.gym.gymapp.services.nutrition_facts;

import com.gym.gymapp.model.recipe.Ingredient;
import com.gym.gymapp.model.recipe.NutritionFacts;
import com.gym.gymapp.model.recipe.Recipe;
import com.gym.gymapp.repositories.NutritionFactsRepository;
import com.gym.gymapp.repositories.RecipeRepository;
import com.gym.gymapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class NutritionFactsImpl  implements NutritionFactsService{
    private final NutritionFactsRepository nutritionFactsRepository;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;


    @Autowired
    public NutritionFactsImpl(
            NutritionFactsRepository nutritionFactsRepository,
            UserRepository userRepository,
            RecipeRepository recipeRepository
    ) {
        this.nutritionFactsRepository = nutritionFactsRepository;
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;

    }

    @Override
    public Optional<NutritionFacts> createNutritionFacts(NutritionFacts newNutritionFacts, Long recipeid) {
        if (nutritionFactsRepository.existsById(newNutritionFacts.getId())) {
            return Optional.empty();
        }
        NutritionFacts nutritionFacts = new NutritionFacts();
        nutritionFacts.setId(newNutritionFacts.getId());
        nutritionFacts.setCalories(newNutritionFacts.getCalories());
        nutritionFacts.setFat(newNutritionFacts.getFat());
        nutritionFacts.setCarbs(newNutritionFacts.getCarbs());
        nutritionFacts.setProtein(newNutritionFacts.getProtein());
        Recipe recipe = recipeRepository.findById(recipeid).orElse(null);

        if (recipe != null) {
            nutritionFacts.setRecipe(recipe);
            return Optional.of(nutritionFactsRepository.save(nutritionFacts));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<NutritionFacts> getNutritionFacts(Long id) {
        return nutritionFactsRepository.findById(id);
    }

    @Override
    public Optional<NutritionFacts> updateNutritionFacts(NutritionFacts newnutritionfacts) {
        Optional<NutritionFacts> existingIngredient = nutritionFactsRepository.findById(newnutritionfacts.getId());

        if (existingIngredient.isPresent()) {
            NutritionFacts nutritionFacts = existingIngredient.get();
            nutritionFacts.setCalories(newnutritionfacts.getCalories());
            nutritionFacts.setFat(newnutritionfacts.getFat());
            nutritionFacts.setCarbs(newnutritionfacts.getCarbs());
            nutritionFacts.setProtein(newnutritionfacts.getProtein());
            Recipe recipe = recipeRepository.findById(nutritionFacts.getRecipe().getId()).orElse(null);

            if (recipe != null) {
                nutritionFacts.setRecipe(recipe);
                return Optional.of(nutritionFactsRepository.save(nutritionFacts));
            } else {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    @Override
    public void deleteNutritionFacts(Long id) {
        Optional<NutritionFacts> exercise = nutritionFactsRepository.findById(id);
        exercise.ifPresent(nutritionFactsRepository::delete);
    }
}


