package com.gym.gymapp.model.exerciselist;

import com.gym.gymapp.model.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "exercise_list")
public class ExerciseList {
    @Id
    private Long id;
    private String name;
    private String first_category;
    private String second_category;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "exercise_list_exercise",
            joinColumns = @JoinColumn(name = "exercise_list_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id")
    )
    private List<Exercise> exercises;
    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private User creator;
}

