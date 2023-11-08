package com.gym.gymapp.controllers;

import com.gym.gymapp.model.exerciselist.ExerciseList;
import com.gym.gymapp.services.exercise_list.ExerciseListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8100")
@RequestMapping("/exercise_list")
public class ExerciseListController {
    private final ExerciseListService exerciseListService;
    @Autowired
    public ExerciseListController(ExerciseListService exerciseListService) {
        this.exerciseListService = exerciseListService;
    }
    @GetMapping("/getlist/{id}")
    public ResponseEntity<Optional<ExerciseList>> MyExerciseList(@PathVariable("id") Long id){
        Optional<ExerciseList> exerciseListList = exerciseListService.getById(id);
        return new ResponseEntity<>(exerciseListList, HttpStatus.OK);
    }
    @GetMapping("/listmyexerciselist/{userid}")
    public  ResponseEntity<List<ExerciseList>> listMyAllExerciseList(@PathVariable("userid") Long userid){
        List<ExerciseList> exerciseListList = exerciseListService.getMyAllExerciseList(userid);
        return new ResponseEntity<>(exerciseListList, HttpStatus.OK);
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
            Optional<ExerciseList> updatedExerciseList = exerciseListService.updateExerciseList(exerciseList,userid);
            if (updatedExerciseList.isPresent()) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>( e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   @DeleteMapping("/delete/{listid}")
    public ResponseEntity<Void> deleteList(@PathVariable Long listid) {
        Optional<ExerciseList> exerciseList = exerciseListService.getById(listid);
        if (exerciseList.isPresent()) {
            exerciseListService.deleteExerciseList(exerciseList.get().getId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
