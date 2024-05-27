package com.saccess.eventAndDonation.dto;

public record Userdto(
        Long id,
        String firstName,
        String lastName,
        String email,
        String preferences
) {
}
