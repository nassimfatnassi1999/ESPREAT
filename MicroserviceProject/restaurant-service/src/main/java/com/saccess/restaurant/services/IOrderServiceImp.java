package com.saccess.restaurant.services;

import com.saccess.restaurant.entities.Dish;
import com.saccess.restaurant.entities.DishOrder;
import com.saccess.restaurant.repositories.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IOrderServiceImp implements IOrderService{

    @Autowired
    IOrderRepository iOrderRepository ;

    @Override
    public List<DishOrder> retrieveAllOrders() {
        return iOrderRepository.findAll();
    }

    @Override
    public DishOrder createOrder(DishOrder order) {
        return iOrderRepository.save(order);
    }

    @Override
    public DishOrder updateOrder(DishOrder order) {
        return iOrderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        iOrderRepository.deleteById(id);
    }

    @Override
    public DishOrder retrieveOrder(Long id) {
        return iOrderRepository.findById(id).orElse(null);
    }
}
