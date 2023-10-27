package com.gym.gymapp.services;

import com.gym.gymapp.model.Exercise;
import com.gym.gymapp.model.User;
import com.gym.gymapp.repositories.ExerciseRepository;
import com.gym.gymapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ExerciseImpl implements ExerciseService{
    private final ExerciseRepository exerciseRepository;
    private final UserRepository userRepository;

    @Autowired
    public ExerciseImpl(ExerciseRepository exerciseRepository, UserRepository userRepository) {
        this.exerciseRepository = exerciseRepository;
        this.userRepository = userRepository;
    }
    @Override
    public Optional<Exercise> createExercise(Exercise newexercise,Long userid) {
        Exercise exercise = new Exercise();
        exercise.setPr(newexercise.getPr());
        exercise.setFirst_category(newexercise.getFirst_category());
        exercise.setSecond_category(newexercise.getSecond_category());
        exercise.setName(newexercise.getName());
        exercise.setDistance(newexercise.getDistance());
        exercise.setWeight(newexercise.getWeight());
        exercise.setRepetitions(newexercise.getRepetitions());
        exercise.setSets(newexercise.getSets());
        User user = userRepository.findById(userid).orElse(null);

        if (user != null) {
            exercise.setCreator(user);
            return Optional.of(exerciseRepository.save(exercise));
        } else {
            return Optional.empty();
        }
    }
    @Override
    public List<Exercise> myExercises(Long id){
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            return exerciseRepository.findMyExercises(id);
        } else {
            return null;
        }
    }

}
