package com.gym.gymapp.controllers;

import com.gym.gymapp.model.Exercise;
import com.gym.gymapp.model.ExerciseList;
import com.gym.gymapp.services.ExerciseListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8100")
@RequestMapping("/exercise_list")
public class ExerciseListController {
    private ExerciseListService exerciseListService;
    @Autowired
    public ExerciseListController(ExerciseListService exerciseListService) {
        this.exerciseListService = exerciseListService;
    }

    @PostMapping("/save/{userid}")
    public ResponseEntity<String> createExerciseList(@RequestBody ExerciseList exerciseList, @PathVariable("userid") Long userid) {
        try {
            Optional<ExerciseList> createExerciseList = exerciseListService.createExerciseList(exerciseList,userid);
            if (createExerciseList.isPresent()) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>( e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/update/{userid}")
    public ResponseEntity<String> updateExerciseList(@RequestBody ExerciseList exerciseList, @PathVariable("userid") Long userid) {
        try {
            Optional<ExerciseList> createExerciseList = exerciseListService.createExerciseList(exerciseList,userid);
            if (createExerciseList.isPresent()) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>( e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
