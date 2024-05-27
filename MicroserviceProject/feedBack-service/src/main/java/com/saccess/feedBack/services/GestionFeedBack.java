package com.saccess.feedBack.services;

import com.saccess.feedBack.clients.UserClient;
import com.saccess.feedBack.dto.FullRes;
import com.saccess.feedBack.dto.Restodto;
import com.saccess.feedBack.dto.UFeedback;
import com.saccess.feedBack.dto.Userdto;
import com.saccess.feedBack.entities.Feedback;
import com.saccess.feedBack.entities.Status;
import com.saccess.feedBack.entities.Type;
import com.saccess.feedBack.repositories.IFeedBackRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class GestionFeedBack implements IGestionFeedBack {
    @Autowired
    private JavaMailSender emailSender;
    public  void sendFeedBackNotification(String toEmail) {
        SimpleMailMessage message  = new SimpleMailMessage();
        message.setFrom("*-----*-----*----*");
        message.setSubject("Confirmation de réception de votre feedback");
        message.setTo(toEmail);
        message.setText( "\"Cher utilisateur,\" \n" +
                "                \"Nous avons bien reçu votre feedback et nous vous en remercions. Votre opinion est précieuse pour nous \" \n" +
                "                \"et nous l'utiliserons pour améliorer nos services. Si vous avez d'autres questions ou commentaires, \" \n" +
                "                \"n'hésitez pas à nous contacter.\"\n" +
                "                \"Cordialement,\" \n" +
                "                \"ESPREAT\"");
        emailSender.send(message);
    }

    @Autowired
    IFeedBackRepository feedBackRepository;

    UserClient userClient;
    @Override
    public List<Feedback> retrieveAllFeedbacks() {

        return feedBackRepository.findAll();
    }

    @Transactional
    @Override
    public Feedback addFeedBack(Feedback feedback,long id_rest) {
        LocalDate currentDate = LocalDate.now();
        Date date = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        feedback.setCreatedAt(date);
        String email= findUserById(feedback.getUser_id()).email();
        sendFeedBackNotification(email);
        feedback.setId_restaurant(id_rest);
        return feedBackRepository.save(feedback);

    }

    @Override
    public Feedback updateFeedback(Feedback feedback) {

        return feedBackRepository.save(feedback);
    }

    @Override
    public Feedback retrieveFeedback(Long idfb) {
        return feedBackRepository.findById(idfb).get();
    }
    @Override
    public List<Feedback> findByDateCreation(Date creatDate) {
        List<Feedback> filteredFeedbacks = new ArrayList<>();
        List<Feedback> allFeedbacks = feedBackRepository.findAll();

        for (Feedback feedback : allFeedbacks) {
            Date createdAt = feedback.getCreatedAt();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/DD");
            String feedbackDateStr = sdf.format(createdAt);
            String targetDateStr = sdf.format(creatDate);
            if (feedbackDateStr.equals(targetDateStr)) {
                filteredFeedbacks.add(feedback);
            }
        }

        return filteredFeedbacks;
    }

    public List<Feedback> findByMounthCreation(Date creatDate) {
        List<Feedback> filteredFeedbacks = new ArrayList<>();
        List<Feedback> allFeedbacks = feedBackRepository.findAll();

        for (Feedback feedback : allFeedbacks) {
            Date createdAt = feedback.getCreatedAt();
            SimpleDateFormat sdf = new SimpleDateFormat("MM");
            String feedbackMonth = sdf.format(createdAt);
            String targetMonth = sdf.format(creatDate);

            if (feedbackMonth.equals(targetMonth)) {
                filteredFeedbacks.add(feedback);
            }
        }

        return filteredFeedbacks;
    }
    @Override
    public void removeFeedback(Long fbid) {
        feedBackRepository.deleteById(fbid);
    }

    @Override
    public Userdto findUserById(Long userid){
        var user = userClient.getUserById(userid);
        return user;
    }
    @Override
    public List<Feedback> findByStatus(Status status) {
        List<Feedback> filteredFeedbacks = new ArrayList<>();
        List<Feedback> allFeedbacks = feedBackRepository.findAll();

        for (Feedback feedback : allFeedbacks) {
            if (feedback.getStatus() == status) {
                filteredFeedbacks.add(feedback);
            }
        }

        return filteredFeedbacks;
    }
    @Override
    public List<Feedback> findByType(Type type) {
        List<Feedback> filteredFeedbacks = new ArrayList<>();
        List<Feedback> allFeedbacks = feedBackRepository.findAll();

        for (Feedback feedback : allFeedbacks) {
            if (feedback.getType() == type) {
                filteredFeedbacks.add(feedback);
            }
        }

        return filteredFeedbacks;
    }

    @Override
    public void updateFeedbackDescription(Long feedbackId, String newDescription) {

        Optional<Feedback> optionalFeedback = feedBackRepository.findById(feedbackId);

        if (optionalFeedback.isPresent()) {
            Feedback feedback = optionalFeedback.get();
            feedback.setDescription(newDescription);


            feedBackRepository.save(feedback);
        } else {

        }
    }
    public List<Feedback> findRecentlyUpdatedFeedbacks(int nbr) {
        List<Feedback> feedbacks = feedBackRepository.findAllByOrderByUpdatedAtDesc();

        List<Feedback> updatedFeedbacks = feedbacks.subList(0, Math.min(nbr, feedbacks.size()));

        return updatedFeedbacks;
    }

    @Override
    public List<Userdto> getAllUser() {
        return userClient.getAllUser();
    }
    @Override
    public FullRes getUserAndFeedback(Long id) {

            Userdto user = userClient.getUserById(id); //user atteint
            List<Feedback> feedbacks = feedBackRepository.getAllFeedbackbyUserId(id);

            return new FullRes(feedbacks,user);
        }

}