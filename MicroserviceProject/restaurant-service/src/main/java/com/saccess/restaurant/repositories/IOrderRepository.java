package com.saccess.restaurant.repositories;

import com.saccess.restaurant.entities.DishOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<DishOrder, Long> {
}
