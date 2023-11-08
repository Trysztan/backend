package com.gym.gymapp.services.exercise;

import com.gym.gymapp.model.exerciselist.Exercise;


import java.util.List;
import java.util.Optional;

public interface ExerciseService {
    Optional<Exercise> createExercise(Exercise exercise,Long userid);
    List<Exercise> myExercises(Long id);

    Optional<Exercise> getExercise(Long id);

    Optional<Exercise> updateExercise(Exercise exercise);

    boolean areEquals(Exercise oldExercise,Exercise newExercise);

    void deleteExercise(Long id);

    void deleteAllExercises(List<Exercise> exercises);

}
