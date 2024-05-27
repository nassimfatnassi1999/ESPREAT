package com.saccess.restaurant.dto;

import com.saccess.restaurant.entities.Restaurant;

public record DishDTO(
        Long id_dish,
        String name,
        String description,
        double price,
        String photo,
        String category,
        int orders,
        Restaurant restaurant

) {}
