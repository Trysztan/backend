package com.gym.gymapp.services.exercise_list;

import com.gym.gymapp.model.exerciselist.ExerciseList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ExerciseListService {
     Optional<ExerciseList> getById(Long id);
    Optional<ExerciseList> createExerciseList(ExerciseList exerciseList,Long userid);
    List<ExerciseList> getMyAllExerciseList(Long id);

    Optional<ExerciseList> updateExerciseList(ExerciseList exerciseList,Long userid);

    void deleteExerciseList(Long id);
}
