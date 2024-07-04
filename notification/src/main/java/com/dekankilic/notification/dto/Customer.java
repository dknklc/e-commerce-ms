package com.dekankilic.notification.dto;

public record Customer(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
