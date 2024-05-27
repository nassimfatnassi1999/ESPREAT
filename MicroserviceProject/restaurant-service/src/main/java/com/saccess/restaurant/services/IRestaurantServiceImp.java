package com.saccess.restaurant.services;
import com.saccess.restaurant.entities.Dish;
import com.saccess.restaurant.entities.Restaurant;
import com.saccess.restaurant.repositories.IDishRepository;
import com.saccess.restaurant.repositories.IRestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class IRestaurantServiceImp implements IRestaurantService {

    @Autowired
    private IRestaurantRepository iRestaurantRepository;
    @Autowired

    private IDishRepository iDishRepository;


    @Override
    public List<Restaurant> retrieveAllRestaurants() {
        return (List<Restaurant>) iRestaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> retrieveAllRestaurantsByOrders() {
        return (List<Restaurant>) iRestaurantRepository.findAllOrdered();
    }

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        return iRestaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Restaurant restaurant) {
        return iRestaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant retrieveRestaurant(Long id) {
        return iRestaurantRepository.findById(id).orElse(null);
    }

    @Override
    public void removeRestaurant(Long id) {
        iRestaurantRepository.deleteById(id);
    }
    public Dish addDishToRestaurant(Long id, Dish dish) {
        Restaurant restaurant = iRestaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id " + id));

        dish.setRestaurant(restaurant);
        return iDishRepository.save(dish);
    }

    @Override
    public List<Dish> getDishesByCategory(Long id, String category) {
        // Fetch the restaurant by its id
        Restaurant restaurant = iRestaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found with id: " + id));

        // Filter dishes by the specified category
        List<Dish> dishesByCategory = iRestaurantRepository.findById(id).get().getMenu().stream()
                .filter(dish -> dish.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());

        return dishesByCategory;
    }

    @Override
    public List<Dish> getDishesByRestaurantId(Long id_restaurant) {
        return iRestaurantRepository.findById(id_restaurant)
                .map(Restaurant::getMenu)
                .orElse(null);
    }
}
