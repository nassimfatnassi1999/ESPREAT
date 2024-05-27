package com.saccess.eventAndDonation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_event;
    private String title;
    @Enumerated(EnumType.STRING)
    private Type type;
    @Column(nullable = false)
    private String topic;
    @OneToOne(cascade = CascadeType.MERGE)
    private Image_Event image;
    private String location;
   // @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    // private List<Userdto> sponsorsList;
    private  Long user_id;


}
