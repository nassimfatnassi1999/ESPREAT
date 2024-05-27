package com.saccess.eventAndDonation.service;

import com.saccess.eventAndDonation.entities.Donation;

import java.util.List;

public interface IGestionDonation {
    List<Donation> retrieveAllDonations();
    Donation retrieveDonation (Long id_don);
    Donation addDonation(Donation donation);
    Donation updateDonation (Donation donation);
    void removeDonation (Long id_don);

}
