package com.gym.gymapp.controllers;

import com.gym.gymapp.model.exerciselist.Exercise;
import com.gym.gymapp.services.exercise.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8100")
@RequestMapping("/exercise")
public class ExerciesController {
    private ExerciseService exerciseService;
    @Autowired
    public ExerciesController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/getexercise/{id}")
    public ResponseEntity<Optional<Exercise>> MyExerciseList(@PathVariable("id") Long id){
        Optional<Exercise> exercise = exerciseService.getExercise(id);
        return new ResponseEntity<>(exercise, HttpStatus.OK);
    }
    @PostMapping("/save/{userid}")
    public ResponseEntity<String> createExercise(@RequestBody Exercise exercise,@PathVariable("userid") Long userid) {
        try {
            Optional<Exercise> createExercise = exerciseService.createExercise(exercise,userid);
            if (createExercise.isPresent()) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>( e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateExercise(@RequestBody Exercise exercise) {
        try {
            Optional<Exercise> createExercise = exerciseService.updateExercise(exercise);
            if (createExercise.isPresent()) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>( e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/myexercises/{userid}")
    public List<Exercise> getMyExercises(@PathVariable("userid") Long userid){
        return exerciseService.myExercises(userid);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<Exercise> exercise = exerciseService.getExercise(id);
        if (exercise.isPresent()) {
            exerciseService.deleteExercise(exercise.get().getId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
