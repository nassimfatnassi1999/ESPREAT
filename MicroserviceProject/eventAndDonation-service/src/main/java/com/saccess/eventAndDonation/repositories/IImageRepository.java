package com.saccess.eventAndDonation.repositories;

import com.saccess.eventAndDonation.entities.Donation;
import com.saccess.eventAndDonation.entities.Image_Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageRepository extends JpaRepository<Image_Event, Long> {
}
