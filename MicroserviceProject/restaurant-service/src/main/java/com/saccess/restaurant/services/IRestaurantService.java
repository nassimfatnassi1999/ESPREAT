package com.saccess.restaurant.services;

import com.saccess.restaurant.entities.Dish;
import com.saccess.restaurant.entities.Restaurant;

import java.util.List;

public interface IRestaurantService {
    public List<Restaurant> retrieveAllRestaurants() ;
    public List<Restaurant> retrieveAllRestaurantsByOrders() ;
    public Restaurant addRestaurant(Restaurant restaurant) ;
    public Restaurant updateRestaurant(Restaurant restaurant) ;
    public void removeRestaurant(Long id_restaurant) ;
    public Restaurant retrieveRestaurant(Long id_restaurant) ;
    List<Dish> getDishesByRestaurantId(Long id_restaurant);
    public Dish addDishToRestaurant(Long id, Dish dish) ;


    List<Dish> getDishesByCategory(Long id, String category);
}

