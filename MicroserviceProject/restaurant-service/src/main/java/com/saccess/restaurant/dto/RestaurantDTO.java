package com.saccess.restaurant.dto;

public record RestaurantDTO(
        Long id,
        String name,
        String logo,
        float averageRating,
        float waitTime,
        String contactInfo,
        boolean delivery
) {}