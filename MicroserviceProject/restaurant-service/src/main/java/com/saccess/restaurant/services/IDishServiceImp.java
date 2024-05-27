package com.saccess.restaurant.services;

import com.saccess.restaurant.entities.Dish;
import com.saccess.restaurant.repositories.IDishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IDishServiceImp implements IDishService {

    @Autowired
    private IDishRepository iDishRepository;

    @Override
    public List<Dish> retrieveAllDishes() {
        return iDishRepository.findAll();
    }

    @Override
    public Dish createDish(Dish dish) {
        return iDishRepository.save(dish);
    }


    @Override
    public Dish updateDish(Dish dish) {
        return iDishRepository.save(dish);
    }

    @Override
    public void deleteDish(Long id_dish) {
        iDishRepository.deleteById(id_dish);

    }

    @Override
    public Dish retrieveDish(Long id) {
        return iDishRepository.findById(id).orElse(null);
    }


}
