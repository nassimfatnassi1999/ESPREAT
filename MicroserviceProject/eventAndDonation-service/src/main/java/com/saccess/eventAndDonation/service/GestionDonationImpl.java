package com.saccess.eventAndDonation.service;

import com.saccess.eventAndDonation.entities.Donation;
import com.saccess.eventAndDonation.repositories.IDonationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class GestionDonationImpl implements IGestionDonation{
    IDonationRepository donationRepository;


    @Override
    public List<Donation> retrieveAllDonations() {
        return donationRepository.findAll();
    }

    @Override
    public Donation retrieveDonation(Long id_don) {
        return donationRepository.findById(id_don).orElse(null);
    }

    @Override
    public Donation addDonation(Donation donation) {
        return donationRepository.save(donation);
    }

    @Override
    public Donation updateDonation(Donation donation) {
        return donationRepository.save(donation);
    }

    @Override
    public void removeDonation(Long id_don) {
        donationRepository.deleteById(id_don);

    }

}
