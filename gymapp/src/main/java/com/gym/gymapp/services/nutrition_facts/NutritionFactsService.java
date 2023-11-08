package com.gym.gymapp.services.nutrition_facts;

import com.gym.gymapp.model.recipe.Ingredient;
import com.gym.gymapp.model.recipe.NutritionFacts;

import java.util.Optional;

public interface NutritionFactsService {
    Optional<NutritionFacts> createNutritionFacts(NutritionFacts nutritionFacts, Long userid);
    Optional<NutritionFacts> getNutritionFacts(Long id);

    Optional<NutritionFacts> updateNutritionFacts(NutritionFacts nutritionFacts);

    void deleteNutritionFacts(Long id);
}