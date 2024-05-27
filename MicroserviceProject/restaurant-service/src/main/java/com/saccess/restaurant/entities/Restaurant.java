package com.saccess.restaurant.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Restaurant {
    public enum RestaurantCategory {
        Fast_Food,
        Cafe_Restaurant,
        Pizza,
        Cafe,
        OTHER,
        MLAWI
    }
    public enum Badge {
        Best_Seller,
        Best_Donnor,
        New
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_restaurant;
    private String name;
    private String logo;
    @Enumerated(EnumType.STRING)
    private RestaurantCategory category;
    private float averageRating;
    private  int total_orders;
    @OneToOne
    private Location location;
    private float waitTime;
    private boolean isEcoFriendly;
    private String contactInfo;
    private boolean delivery;
    @Enumerated(EnumType.STRING)
    private Badge badge;
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Dish> menu;

}
