package com.gym.gymapp.repositories;

import com.gym.gymapp.model.Exercise;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ExerciseRepository extends CrudRepository<Exercise,Long> {
    @Query("SELECT e FROM Exercise e WHERE e.creator = :userid")
    List<Exercise> findMyExercises(@Param("userid") Long userid);
}
