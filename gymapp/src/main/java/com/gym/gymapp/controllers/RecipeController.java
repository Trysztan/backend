package com.gym.gymapp.controllers;

import com.gym.gymapp.model.User;
import com.gym.gymapp.model.exerciselist.First_Exercise_Category;
import com.gym.gymapp.model.recipe.Recipe;
import com.gym.gymapp.model.recipe.Unit_Of_Measure_Category;
import com.gym.gymapp.services.recipe.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
@RestController
@CrossOrigin(origins = "http://localhost:8100")
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;
    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/uom")
    public Unit_Of_Measure_Category[] getUOM() {
        return Unit_Of_Measure_Category.values();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeByID(@PathVariable("id") Long id) {
        Optional<Recipe> recipe = recipeService.getById(id);
        if (recipe.isPresent()) {
            recipeService.getRecipePic(recipe.get().getId());
            return new ResponseEntity<>(recipe.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/listmyexerciselist/{userid}")
    public  ResponseEntity<List<Recipe>> listMyAllRecipe(@PathVariable("userid") Long userid){
        List<Recipe> recipes = recipeService.getMyAllRecipe(userid);
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }
    @PostMapping("/save/{userid}")
    public ResponseEntity<String> createRecipe(@RequestBody Recipe recipe, @PathVariable("userid") Long userid) {
        try {
            Optional<Recipe> createExerciseList = recipeService.creatRecipe(recipe,userid);
            if (createExerciseList.isPresent()) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>( e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/update/{userid}")
    public ResponseEntity<String> updateRecipe(
            @RequestBody Recipe recipe,
            @PathVariable("userid") Long userid,
            @RequestParam(value = "file", required = false) MultipartFile file
    )
    {
        try {
            Optional<Recipe> updatedExerciseList = recipeService.updateRecipe(recipe,userid,file);
            if (updatedExerciseList.isPresent()) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>( e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/delete/{listid}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long listid) {
        Optional<Recipe> exerciseList = recipeService.getById(listid);
        if (exerciseList.isPresent()) {
            recipeService.deleteRecipe(exerciseList.get().getId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

