package com.gym.gymapp.controllers;

import com.gym.gymapp.model.exerciselist.First_Exercise_Category;
import com.gym.gymapp.model.exerciselist.Second_Exercise_Category;
import com.gym.gymapp.services.exercise_category.ExerciseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8100")
@RequestMapping("/category")
public class ExerciseCategoryController {
    @Autowired
    private ExerciseCategoryService categoryService;

    @GetMapping("/first_cat")
    public First_Exercise_Category[] getCategories() {
        return First_Exercise_Category.values();
    }

    @GetMapping("/second_cat/{fc}")
    public List<Second_Exercise_Category> getSecondCategories(@PathVariable("fc") String firstExerciseCategory) {
        return categoryService.getSecondCategoriesForFirstCategory(firstExerciseCategory);
    }

}
