package com.gym.gymapp.services.exercise;

import com.gym.gymapp.model.exerciselist.Exercise;
import com.gym.gymapp.model.User;
import com.gym.gymapp.repositories.ExerciseRepository;
import com.gym.gymapp.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ExerciseImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final UserRepository userRepository;

    @Autowired
    public ExerciseImpl(ExerciseRepository exerciseRepository, UserRepository userRepository) {
        this.exerciseRepository = exerciseRepository;
        this.userRepository = userRepository;
    }
    @Override
    public Optional<Exercise> createExercise(Exercise newexercise,Long userid) {
        if (exerciseRepository.existsById(newexercise.getId())) {
            return Optional.empty();
        }
        Exercise exercise = new Exercise();
        exercise.setId(newexercise.getId());
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
    @Override
    public Optional<Exercise> getExercise(Long id){
        return exerciseRepository.getById(id);
    }
    @Override
    public boolean areEquals(Exercise oldexercise,Exercise newexercise){
        return oldexercise.getId().equals(newexercise.getId()) &&
                oldexercise.getName().equals(newexercise.getName()) &&
                oldexercise.getFirst_category().equals(newexercise.getFirst_category()) &&
                oldexercise.getSecond_category().equals(newexercise.getSecond_category()) &&
                oldexercise.getPr() == newexercise.getPr() &&
                oldexercise.getWeight() == newexercise.getWeight() &&
                oldexercise.getDistance() == newexercise.getDistance() &&
                oldexercise.getSets() == newexercise.getSets() &&
                oldexercise.getRepetitions() == newexercise.getRepetitions();
    }

    @Override
    public Optional<Exercise> updateExercise(Exercise exercise){
        Optional<Exercise> existingExercise = exerciseRepository.findById(exercise.getId());

        if (existingExercise.isPresent()) {
            Exercise exerciseToUpdate = existingExercise.get();
            exerciseToUpdate.setName(exercise.getName());
            exerciseToUpdate.setFirst_category(exercise.getFirst_category());
            exerciseToUpdate.setSecond_category(exercise.getSecond_category());
            exerciseToUpdate.setPr(Math.max(exercise.getWeight(), exerciseToUpdate.getPr()));
            exerciseToUpdate.setWeight(exercise.getWeight());
            exerciseToUpdate.setDistance(exercise.getDistance());
            exerciseToUpdate.setSets(exercise.getSets());
            exerciseToUpdate.setRepetitions(exercise.getRepetitions());
            exerciseToUpdate.setExerciseLists(exercise.getExerciseLists());
            Exercise updatedExercise = exerciseRepository.save(exerciseToUpdate);
            return Optional.of(updatedExercise);
        } else {
            return Optional.empty();
        }    }
    @Override
    public void deleteExercise(Long id){
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        exercise.ifPresent(exerciseRepository::delete);
    }

    @Override
    @Transactional
    public void deleteAllExercises(List<Exercise> exercises){
        for(Exercise exercise: exercises){
            deleteExercise(exercise.getId());
        }
    }
}
