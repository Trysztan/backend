package com.gym.gymapp.model.exerciselist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gym.gymapp.model.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "exercise")
public class Exercise {
    @Id
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
    @JsonIgnore
    @ManyToMany(mappedBy = "exercises", cascade = CascadeType.ALL)
    private List<ExerciseList> exerciseLists = new ArrayList<>();

}
