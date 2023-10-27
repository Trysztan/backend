package com.gym.gymapp.controllers;

import com.gym.gymapp.model.First_Exercise_Category;
import com.gym.gymapp.model.Second_Exercise_Category;
import com.gym.gymapp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8100")
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/first_cat")
    public First_Exercise_Category[] getCategories() {
        return First_Exercise_Category.values();
    }

    @GetMapping("/second_cat/{fc}")
    public List<Second_Exercise_Category> getSecondCategories(@PathVariable("fc") String firstExerciseCategory) {
        return categoryService.getSecondCategoriesForFirstCategory(firstExerciseCategory);
    }

}
