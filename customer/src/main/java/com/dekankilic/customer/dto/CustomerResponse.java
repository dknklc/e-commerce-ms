package com.dekankilic.customer.dto;

import com.dekankilic.customer.model.Address;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {
}
