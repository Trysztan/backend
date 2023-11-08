package com.gym.gymapp.services.exercise_category;

import com.gym.gymapp.model.exerciselist.First_Exercise_Category;
import com.gym.gymapp.model.exerciselist.Second_Exercise_Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExerciseCategoryImpl implements ExerciseCategoryService {

    @Override
    public List<Second_Exercise_Category> getSecondCategoriesForFirstCategory(String firstCategory) {
        List<Second_Exercise_Category> secondCategories = new ArrayList<>();
        if (firstCategory.equals(First_Exercise_Category.Lowerbody.toString())) {
            secondCategories.add(Second_Exercise_Category.Calves);
            secondCategories.add(Second_Exercise_Category.Legs);
            secondCategories.add(Second_Exercise_Category.Glute);
        }
        if (firstCategory.equals(First_Exercise_Category.Upperbody.toString())) {
            secondCategories.add(Second_Exercise_Category.Abs);
            secondCategories.add(Second_Exercise_Category.Biceps);
            secondCategories.add(Second_Exercise_Category.Triceps);
            secondCategories.add(Second_Exercise_Category.Chest);
            secondCategories.add(Second_Exercise_Category.Shoulder);
            secondCategories.add(Second_Exercise_Category.Back);
        }
        if (firstCategory.equals(First_Exercise_Category.Core.toString())) {
            secondCategories.add(Second_Exercise_Category.Abs);
            secondCategories.add(Second_Exercise_Category.Biceps);
            secondCategories.add(Second_Exercise_Category.Triceps);
            secondCategories.add(Second_Exercise_Category.Chest);
            secondCategories.add(Second_Exercise_Category.Shoulder);
            secondCategories.add(Second_Exercise_Category.Calves);
            secondCategories.add(Second_Exercise_Category.Legs);
            secondCategories.add(Second_Exercise_Category.Glute);
            secondCategories.add(Second_Exercise_Category.Back);
        }

        return secondCategories;
    }
}
