package com.saccess.restaurant.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
    public class Location {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id_location;
        private String address;
        private float latitude;
        private float longitude;
}
