package com.gym.gymapp.repositories;

import com.gym.gymapp.model.exerciselist.ExerciseList;
import com.gym.gymapp.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ExerciseListRepository extends CrudRepository<ExerciseList, Long> {
    @Query("SELECT el FROM ExerciseList el WHERE el.creator = :creator")
    List<ExerciseList> findByCreator(@Param("creator") User user);
    @Query("SELECT el FROM ExerciseList el WHERE el.id = :id")
    Optional<ExerciseList> getById(@Param("id") Long listid);
}
