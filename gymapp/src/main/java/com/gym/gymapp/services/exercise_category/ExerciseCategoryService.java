package com.gym.gymapp.services.exercise_category;

import com.gym.gymapp.model.exerciselist.Second_Exercise_Category;

import java.util.List;

public interface ExerciseCategoryService {

    List<Second_Exercise_Category> getSecondCategoriesForFirstCategory(String firstCategory);
}
