package com.gym.gymapp.repositories;

import com.gym.gymapp.model.ExerciseList;
import com.gym.gymapp.model.User;
import org.springframework.data.repository.CrudRepository;

public interface ExerciseListRepository extends CrudRepository<ExerciseList, Long> {
}
