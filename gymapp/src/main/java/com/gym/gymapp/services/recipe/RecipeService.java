package com.gym.gymapp.services.recipe;

import com.gym.gymapp.model.User;
import com.gym.gymapp.model.exerciselist.ExerciseList;
import com.gym.gymapp.model.recipe.Recipe;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
@Service

public interface RecipeService {
    Optional<Recipe> getById(Long id);
    Optional<Recipe> creatRecipe(Recipe recipe,Long userid);
    List<Recipe> getMyAllRecipe(Long id);

    Optional<Recipe> updateRecipe(Recipe recipe, Long userid, MultipartFile file);

    void deleteRecipe(Long id);

    Optional<Recipe> uploadRecipePic(Long id, MultipartFile file);

    byte[] getRecipePic(Long id);
}

