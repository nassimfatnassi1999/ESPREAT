package com.saccess.eventAndDonation.service;

import com.saccess.eventAndDonation.dto.FullEventUser;
import com.saccess.eventAndDonation.dto.FullResponse;
import com.saccess.eventAndDonation.dto.UEvent;
import com.saccess.eventAndDonation.dto.Userdto;
import com.saccess.eventAndDonation.entities.Donation;
import com.saccess.eventAndDonation.entities.Event;
import com.saccess.eventAndDonation.entities.Type;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IGestionEvent {

    List<Event> retrieveAllEvents();



    Event retrieveEvent (Long id_event);
    Event addEvent(Event event);
    public void addEventWithImage(Event event, MultipartFile imageFile);

    Event updateEvent(Long id, Event updatedvent);
    Userdto findUserById(Long userid);

    List<Event> getEventByType(Type type);
    List<Event> getEventByDate(LocalDate date);
    FullResponse getUserAndEvent(Long id);
    public List<Event> findEventByTitle(String nom);

    public FullEventUser getAllUserEvent();
    List<Userdto> getAllUsers();





    public void deleteImageFromCloudinary(String imageUrl);
    void deleteEvent (Long id_event);
    void deleteEventsByUserId(long user_id);


    String extractImageIdFromUrl(String imageUrl);




}
