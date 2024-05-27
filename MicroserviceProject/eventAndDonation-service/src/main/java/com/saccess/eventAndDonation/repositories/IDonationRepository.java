package com.saccess.eventAndDonation.repositories;

import com.saccess.eventAndDonation.entities.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDonationRepository extends JpaRepository<Donation, Long> {
}
