package com.gym.gymapp.repositories;

import com.gym.gymapp.model.User;
import com.gym.gymapp.model.exerciselist.ExerciseList;
import com.gym.gymapp.model.recipe.Recipe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends CrudRepository<Recipe,Long> {
    @Query("SELECT r FROM Recipe r WHERE r.creator = :creator")
    List<Recipe> findByCreator(@Param("creator") User user);

    @Query("SELECT r FROM ExerciseList r WHERE r.id = :id")
    Optional<Recipe> getById(@Param("id") Long recipeid);
}
