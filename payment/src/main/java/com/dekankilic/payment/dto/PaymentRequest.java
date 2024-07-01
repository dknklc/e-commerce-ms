package com.dekankilic.payment.dto;


import com.dekankilic.payment.model.enums.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest (
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        Customer customer
){
}
