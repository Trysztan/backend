package com.gym.gymapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "exercise")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String first_category;
    private String second_category;
    private int pr;
    private int weight;
    private int distance;
    private int sets;
    private int repetitions;
    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private User creator;
    @ManyToMany
    @JoinTable(
            name = "exercise_list_exercise",
            joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_list_id")
    )
    private List<ExerciseList> exerciseLists;

}
