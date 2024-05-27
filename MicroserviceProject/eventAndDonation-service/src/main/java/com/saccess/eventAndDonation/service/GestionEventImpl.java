package com.saccess.eventAndDonation.service;

import com.cloudinary.utils.ObjectUtils;
import com.saccess.eventAndDonation.clients.UserClient;
import com.saccess.eventAndDonation.dto.FullEventUser;
import com.saccess.eventAndDonation.dto.FullResponse;
import com.saccess.eventAndDonation.dto.Userdto;
import com.saccess.eventAndDonation.entities.Event;
import com.saccess.eventAndDonation.entities.Image_Event;
import com.saccess.eventAndDonation.entities.Type;
import com.saccess.eventAndDonation.repositories.IEventRepository;
import com.saccess.eventAndDonation.repositories.IImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
@AllArgsConstructor

public class GestionEventImpl implements IGestionEvent{

    private CloudinaryService_Event cloudinaryService;

    IEventRepository eventRepository;



    @Autowired
    private IImageRepository imgrepo;

    @Override
    public void deleteEventsByUserId(long user_id){
        eventRepository.deleteAll(eventRepository.getAllEventbyUserId(user_id));
    }


    UserClient userClient;
    @Override
    public Event retrieveEvent(Long id_event) {
        return eventRepository.findById(id_event).orElse(null);
    }
    @Override
    public List<Event> retrieveAllEvents() {
        return eventRepository.findAll();
    }



    @Override
    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }


    @Override
    public void deleteEvent(Long id_event) {
// Récupérer l'ID de l'image associée à la news
        Optional<Event> eventOptional = eventRepository.findById(id_event);
        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            String imageURL = event.getImage().getImageURL();
            Long imageId = event.getImage().getId();
            // Supprimer image from Cloudinary
            deleteImageFromCloudinary(imageURL);
            // Supprimer la news de la base de données
            eventRepository.deleteById(id_event);
            // Supprimer l'entrée de l'image de la base de données
            imgrepo.deleteById(imageId);
        }

    }



    @Override
    public Event updateEvent(Long id, Event updatedvent) {
        Event E = eventRepository.findById(id).orElse(null);
        if (E != null) {
            E.setType(updatedvent.getType());
            E.setTitle(updatedvent.getTitle());
            E.setImage(updatedvent.getImage());
            E.setDate(updatedvent.getDate());
            E.setLocation(updatedvent.getLocation());
            E.setTopic(updatedvent.getTopic());


            return eventRepository.save(E);
        }

        return null;
    }
    @Override
    public Userdto findUserById(Long userid){
        return userClient.getUserById(userid);
    }

    @Override
    public List<Event> getEventByType(Type type) {
        return eventRepository.getEventByType(type);
    }


    @Override
    public List<Event> getEventByDate(LocalDate date) {
        return eventRepository.getEventByDate(date);
    }
    @Override
   public FullResponse getUserAndEvent(Long id) {
        Userdto user = userClient.getUserById(id); //user jebneh
        List<Event> events = eventRepository.getAllEventbyUserId(id);

        return new FullResponse(user,events);
    }

    @Override
    public FullEventUser getAllUserEvent() {
        List<Userdto> userdtos=userClient.getAllUsers();
        List<Event> events= eventRepository.findAll();

        return  new FullEventUser(userdtos,events);
    }

    @Override
    public List<Userdto> getAllUsers() {
        return userClient.getAllUsers();
    }


    @Override
    public List<Event> findEventByTitle(String title) {
        return eventRepository.findBytitle(title);
    }

    @Override
    public void addEventWithImage(Event event, MultipartFile imageFile) {
        try {
            // Enregistrer l'image sur Cloudinary
            Map uploadResult = cloudinaryService.upload(imageFile);
            // 5oudh l'URL de l'image from  Cloudinary
            String imageUrl = (String) uploadResult.get("url");
            // Enregistrer le lien URL de l'image dans events
            Image_Event image = new Image_Event();
            image.setName(imageFile.getOriginalFilename());
            image.setImageURL(imageUrl);
            //save the image
            imgrepo.save(image);
            // set image to events
            event.setImage(image);
            //Enregistrer la nouvelle dans la base de données
            eventRepository.save(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteImageFromCloudinary(String imageUrl) {
        try {
            // 5oudh imageID from URL
            String imageId = extractImageIdFromUrl(imageUrl);
            // Supprimer image from Cloudinary
            cloudinaryService.cloudinary.uploader().destroy(imageId, ObjectUtils.emptyMap());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String extractImageIdFromUrl(String imageUrl) {
        // positionner id mel URl
        int lastSlashIndex = imageUrl.lastIndexOf("/");
        int lastDotIndex = imageUrl.lastIndexOf(".");
        // extract imageID from URL /blablabla
        return imageUrl.substring(lastSlashIndex + 1, lastDotIndex);
    }


}






