package com.gym.gymapp.services.exercise_list;

import com.gym.gymapp.model.exerciselist.Exercise;
import com.gym.gymapp.model.exerciselist.ExerciseList;
import com.gym.gymapp.model.User;
import com.gym.gymapp.repositories.ExerciseListRepository;
import com.gym.gymapp.repositories.ExerciseRepository;
import com.gym.gymapp.repositories.UserRepository;
import com.gym.gymapp.services.exercise.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExerciseListImpl implements ExerciseListService {
    private final ExerciseListRepository exerciseListRepository;
    private final UserRepository userRepository;
    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseListImpl(
            ExerciseListRepository exerciseListRepository,
            UserRepository userRepository,
            ExerciseService exerciseService
    ) {
        this.exerciseListRepository = exerciseListRepository;
        this.userRepository = userRepository;
        this.exerciseService = exerciseService;
    }
    @Override
    public Optional<ExerciseList> getById(Long id) {
        return exerciseListRepository.getById(id);
    }

    @Override
    public Optional<ExerciseList> createExerciseList(ExerciseList newExerciseList, Long userId) {
        if (exerciseListRepository.existsById(newExerciseList.getId())) {
            return Optional.empty();
        }

        ExerciseList exerciseList = new ExerciseList();
        exerciseList.setId(newExerciseList.getId());
        exerciseList.setName(newExerciseList.getName());
        exerciseList.setFirst_category(newExerciseList.getFirst_category());
        exerciseList.setSecond_category(newExerciseList.getSecond_category());

        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            exerciseList.setCreator(user);
            exerciseListRepository.save(exerciseList);

            List<Exercise> exercises = newExerciseList.getExercises();
            for (Exercise exercise : exercises) {
                exercise.getExerciseLists().add(exerciseList);
                exerciseService.createExercise(exercise, user.getId());
            }
            exerciseList.setExercises(newExerciseList.getExercises());
            exerciseListRepository.save(exerciseList);

            return Optional.of(exerciseList);
        } else {
            return Optional.empty();
        }
    }
    @Override
    public Optional<ExerciseList> updateExerciseList(ExerciseList newExerciseList,Long userid) {
        User user = userRepository.findById(userid).orElse(null);
        Optional<ExerciseList> oldexerciseList = exerciseListRepository.findById(newExerciseList.getId());

        if(oldexerciseList.isPresent()) {
            ExerciseList updatedList = oldexerciseList.get();
            if(newExerciseList.getExercises().isEmpty()){
                deleteExerciseList(updatedList.getId());
                return Optional.empty();
            }
            updatedList.setName(newExerciseList.getName());
            updatedList.setFirst_category(newExerciseList.getFirst_category());
            updatedList.setSecond_category(newExerciseList.getSecond_category());
            updatedList.setCreator(user);
            updatedList.setExercises(newExerciseList.getExercises());
            exerciseListRepository.save(updatedList);
            return Optional.of(updatedList);
        }else {
            return Optional.empty();
        }
    }


    @Override
    public  List<ExerciseList> getMyAllExerciseList(Long userid){
        User user = userRepository.findById(userid).orElse(null);
        if (user != null) {
            return exerciseListRepository.findByCreator(user);
        }
        return Collections.emptyList();
    }

    @Override
    public void deleteExerciseList(Long id) {
        Optional<ExerciseList> exerciseListOptional = exerciseListRepository.findById(id);

        if (exerciseListOptional.isPresent()) {
            ExerciseList exerciseList = exerciseListOptional.get();

            exerciseListRepository.delete(exerciseList);
        } else {
            System.out.println("Hiba történt a lista törlése közben");
        }
    }
}
