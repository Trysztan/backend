package com.gym.gymapp.services;

import com.gym.gymapp.model.Second_Exercise_Category;

import java.util.List;

public interface CategoryService {

    List<Second_Exercise_Category> getSecondCategoriesForFirstCategory(String firstCategory);
}
