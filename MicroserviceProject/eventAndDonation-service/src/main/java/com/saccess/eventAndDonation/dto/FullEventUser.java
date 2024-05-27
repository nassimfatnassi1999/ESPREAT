package com.saccess.eventAndDonation.dto;

import com.saccess.eventAndDonation.entities.Event;

import java.util.List;

public record FullEventUser(List<Userdto> userdtos,
                            List<Event> events

) { }
