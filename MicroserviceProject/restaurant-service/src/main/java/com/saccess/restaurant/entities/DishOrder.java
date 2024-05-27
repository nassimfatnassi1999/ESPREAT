package com.saccess.restaurant.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.saccess.restaurant.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class DishOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_order;
    private Float totalPrice;
    private LocalDateTime orderTime;

//    @ManyToOne
//    @JoinColumn(name = "id_user")
   private int id_user;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="dishes")
    private List<Dish> dishes;

    @PrePersist
    protected void onCreate() {
        orderTime = LocalDateTime.now();
    }
}