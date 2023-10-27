package com.gym.gymapp.services;

import com.gym.gymapp.model.ExerciseList;
import com.gym.gymapp.model.User;
import com.gym.gymapp.repositories.ExerciseListRepository;
import com.gym.gymapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ExerciseListImpl implements ExerciseListService{
    private ExerciseListRepository exerciseListRepository;
    private final UserRepository userRepository;

    @Autowired
    public ExerciseListImpl(ExerciseListRepository exerciseListRepository, UserRepository userRepository) {
        this.exerciseListRepository = exerciseListRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Optional<ExerciseList> createExerciseList(ExerciseList newexerciseList,Long userid) {
        if (exerciseListRepository.existsById(newexerciseList.getId())) {
            return Optional.empty();
        }
        ExerciseList exerciseList = new ExerciseList();
        exerciseList.setName(newexerciseList.getName());
        exerciseList.setFirst_category(newexerciseList.getFirst_category());
        exerciseList.setSecond_category(newexerciseList.getSecond_category());
        exerciseList.setExercises(newexerciseList.getExercises());
        User user = userRepository.findById(userid).orElse(null);

        if (user != null) {
            exerciseList.setCreator(user);
            return Optional.of(exerciseListRepository.save(exerciseList));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteUser(Long id) {
        Optional<ExerciseList> exerciseList = exerciseListRepository.findById(id);
        exerciseList.ifPresent(value -> exerciseListRepository.delete(value));
        }
}
