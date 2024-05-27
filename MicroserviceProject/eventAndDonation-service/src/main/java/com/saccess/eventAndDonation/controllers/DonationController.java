package com.saccess.eventAndDonation.controllers;

import com.saccess.eventAndDonation.entities.Donation;
import com.saccess.eventAndDonation.service.IGestionDonation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donation")

public class DonationController {

    @Autowired
    IGestionDonation gestionDonation;

    @GetMapping("/getall")
    public List<Donation> getall(){

        return gestionDonation.retrieveAllDonations();
    }

    @PostMapping("/adddonation")
    public Donation addmoniteur(@RequestBody Donation donation){

        return gestionDonation.addDonation(donation);
    }

    @GetMapping("/getdonation/{id}")
    public Donation getdonation(@PathVariable("id")long id ){

        return gestionDonation.retrieveDonation(id);
    }

    @PutMapping("/updatedonation")
    public void updatedonation(@RequestBody Donation donation){
        gestionDonation.updateDonation(donation);
    }






}
