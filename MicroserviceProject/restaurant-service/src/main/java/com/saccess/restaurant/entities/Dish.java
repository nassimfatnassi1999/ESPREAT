package com.saccess.restaurant.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Dish {
    public enum DishCategory {
        Malfouf,
        Mlawi,
        Chapati,
        Fricasse,
        Crepe,
        Pizza,
        Makloub,
        Sandwich;

        public boolean equalsIgnoreCase(String category) {
            // Convert the provided category string to lowercase for case-insensitive comparison
            String lowercaseCategory = category.toLowerCase();

            // Iterate over each enum value and compare it with the lowercase provided category
            for (DishCategory dishCategory : values()) {
                // Convert each enum value to lowercase for case-insensitive comparison
                String lowercaseEnumValue = dishCategory.name().toLowerCase();

                // Check if the lowercase enum value matches the lowercase provided category
                if (lowercaseEnumValue.equals(lowercaseCategory)) {
                    return true; // Return true if a match is found
                }
            }

            return false; // Return false if no match is found
        }





}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_dish;
    private String name;
    private String description;
    private float price;
    private String photo;
    private int orders;
    @Enumerated(EnumType.STRING)
    private DishCategory category;
    @JsonIgnore
    @ManyToOne
    DishOrder dishes;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_restaurant")
    private Restaurant restaurant;

}
