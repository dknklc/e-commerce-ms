package com.dekankilic.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
public record ErrorResponseDto(
        String apiPath,
        HttpStatus errorCode,
        String errorMessage,
        LocalDateTime errorTime

) {
}
