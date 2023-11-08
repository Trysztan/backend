package com.gym.gymapp.repositories;

import com.gym.gymapp.model.exerciselist.Exercise;
import com.gym.gymapp.model.exerciselist.ExerciseList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ExerciseRepository extends CrudRepository<Exercise,Long> {
    @Query("SELECT e FROM Exercise e WHERE e.creator = :userid")
    List<Exercise> findMyExercises(@Param("userid") Long userid);
    @Query("SELECT e FROM Exercise e WHERE e.exerciseLists = :list")
    List<Exercise> findListExercises(@Param("list") ExerciseList exerciseList);

    @Query("SELECT e FROM Exercise e WHERE e.id = :id")
    Optional<Exercise> getById(@Param("id") Long listid);
}
