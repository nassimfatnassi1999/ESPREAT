package com.saccess.restaurant.controllers;

import com.saccess.restaurant.entities.DishOrder;
import com.saccess.restaurant.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping
    public ResponseEntity<List<DishOrder>> getAllOrders() {
        List<DishOrder> orders = orderService.retrieveAllOrders();
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<DishOrder> createOrder(@RequestBody DishOrder order) {
        DishOrder createdOrder = orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishOrder> getOrderById(@PathVariable Long id) {
        DishOrder order = orderService.retrieveOrder(id);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DishOrder> updateOrder(@PathVariable Long id, @RequestBody DishOrder order) {
        order.setId_order(id);
        DishOrder updatedOrder = orderService.updateOrder(order);
        if (updatedOrder != null) {
            return ResponseEntity.ok(updatedOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
