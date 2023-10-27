package com.gym.gymapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "exercise_list")
public class ExerciseList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String first_category;
    private String second_category;
    @ManyToMany(mappedBy = "exerciseLists")
    private List<Exercise> exercises;
    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private User creator;
}

