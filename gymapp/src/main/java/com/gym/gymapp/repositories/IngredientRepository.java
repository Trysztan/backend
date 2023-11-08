package com.gym.gymapp.repositories;

import com.gym.gymapp.model.recipe.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient,Long> {
}
