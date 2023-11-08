package com.gym.gymapp.model.recipe;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "nutrition_facts")
public class NutritionFacts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int calories;
    private int fat;
    private int carbs;
    private int protein;
    @ManyToOne
    @JoinColumn(name = "recipe_id",  referencedColumnName = "id")
    private Recipe recipe;
}
