package com.saccess.eventAndDonation.dto;

import com.saccess.eventAndDonation.entities.Event;
import com.saccess.eventAndDonation.entities.Image_Event;
import com.saccess.eventAndDonation.entities.Type;
import jakarta.persistence.*;

import java.time.LocalDate;

public record UEvent(Long id_event, String title,Type type,String topic,Image_Event image,String location,LocalDate date, Userdto user) {
}
