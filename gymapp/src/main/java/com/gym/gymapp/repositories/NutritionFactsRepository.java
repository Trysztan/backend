package com.gym.gymapp.repositories;


import com.gym.gymapp.model.recipe.NutritionFacts;
import org.springframework.data.repository.CrudRepository;

public interface NutritionFactsRepository extends CrudRepository<NutritionFacts,Long> {
}
