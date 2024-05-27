package com.saccess.restaurant.repositories;

import com.saccess.restaurant.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query("SELECT r FROM Restaurant r ORDER BY r.total_orders DESC LIMIT 6")
    List<Restaurant> findAllOrdered();
}
