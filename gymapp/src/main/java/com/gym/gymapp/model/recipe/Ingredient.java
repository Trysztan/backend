package com.gym.gymapp.model.recipe;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float quantity;
    private String name;
    private String unit_of_measure;
    private String description;
    @ManyToOne
    @JoinColumn(name = "recipe_id",  referencedColumnName = "id")
    private Recipe recipe;
}
