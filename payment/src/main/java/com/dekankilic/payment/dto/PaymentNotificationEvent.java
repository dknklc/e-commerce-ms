package com.dekankilic.payment.dto;

import com.dekankilic.payment.model.enums.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotificationEvent(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail
) {
}
