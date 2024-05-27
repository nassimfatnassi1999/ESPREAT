package com.saccess.restaurant.controllers;

import com.saccess.restaurant.dto.DishDTO;
import com.saccess.restaurant.dto.RestaurantDTO;
import com.saccess.restaurant.entities.Dish;
import com.saccess.restaurant.services.IDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant/dishes")
public class DishController {

    @Autowired
    private IDishService dishService;

    @GetMapping
    public ResponseEntity<List<DishDTO>> getAllDishes() {
        List<DishDTO> dishes = dishService.retrieveAllDishes().stream().map(dish -> new DishDTO(
                dish.getId_dish(),
                dish.getName(),
                dish.getDescription(),
                dish.getPrice(),
                dish.getPhoto(),
                String.valueOf(dish.getCategory()),
                dish.getOrders(),
                dish.getRestaurant())).toList();
        return ResponseEntity.ok(dishes);
    }

    @PostMapping
    public ResponseEntity<Dish> createDish(@RequestBody Dish dish) {
        Dish createdDish = dishService.createDish(dish);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDish);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dish> getDishById(@PathVariable Long id) {
        Dish dish = dishService.retrieveDish(id);
        if (dish != null) {
            return ResponseEntity.ok(dish);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dish> updateDish(@PathVariable Long id, @RequestBody Dish dish) {
        dish.setId_dish(id); // Ensure the correct ID is set
        Dish updatedDish = dishService.updateDish(dish);
        if (updatedDish != null) {
            return ResponseEntity.ok(updatedDish);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}/increment-orders")
    public ResponseEntity<Dish> incrementDishOrders(@PathVariable Long id, @RequestParam int incrementBy) {
        Dish dish = dishService.retrieveDish(id);
        if (dish == null) {
            return ResponseEntity.notFound().build();
        }

        // Increment orders of the dish
        dish.setOrders(dish.getOrders() + incrementBy);
        dishService.updateDish(dish);

        return ResponseEntity.ok(dish);
    }
}
