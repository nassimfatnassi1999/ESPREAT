package com.saccess.restaurant.repositories;

import com.saccess.restaurant.entities.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDishRepository extends JpaRepository<Dish, Long> {
}
