package com.saccess.restaurant.services;

import com.saccess.restaurant.entities.Dish;

import java.util.List;

public interface IDishService {
    List<Dish> retrieveAllDishes();
    Dish createDish(Dish dish);
    Dish updateDish(Dish dish);
    void deleteDish(Long id_dish);
    Dish retrieveDish(Long id_dish);
}
