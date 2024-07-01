package com.dekankilic.order.dto;

import com.dekankilic.order.model.enums.PaymentMethod;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record OrderResponse(
        Integer id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerId
) {
}
