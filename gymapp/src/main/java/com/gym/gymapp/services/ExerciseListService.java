package com.gym.gymapp.services;

import com.gym.gymapp.model.ExerciseList;
import com.gym.gymapp.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public interface ExerciseListService {
    Optional<ExerciseList> createExerciseList(ExerciseList exerciseList,Long userid);

    void deleteUser(Long id);
}
