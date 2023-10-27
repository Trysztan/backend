package com.gym.gymapp.services;

import com.gym.gymapp.model.Exercise;
import com.gym.gymapp.model.User;

import java.util.List;
import java.util.Optional;

public interface ExerciseService {
    Optional<Exercise> createExercise(Exercise exercise,Long userid);
    List<Exercise> myExercises(Long id);
}
