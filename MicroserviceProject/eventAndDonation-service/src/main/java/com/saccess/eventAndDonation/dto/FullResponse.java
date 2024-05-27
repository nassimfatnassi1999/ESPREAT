package com.saccess.eventAndDonation.dto;

import com.saccess.eventAndDonation.entities.Event;

import java.util.List;

public record FullResponse(
        Userdto user,
        List<Event> events

) {
}
