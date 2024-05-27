package com.saccess.restaurant.services;

import com.saccess.restaurant.entities.Dish;
import com.saccess.restaurant.entities.DishOrder;

import java.util.List;

public interface IOrderService {
    List<DishOrder> retrieveAllOrders();
    DishOrder createOrder(DishOrder order);
    DishOrder updateOrder(DishOrder order);
    void deleteOrder(Long id);
    DishOrder retrieveOrder(Long id);
}