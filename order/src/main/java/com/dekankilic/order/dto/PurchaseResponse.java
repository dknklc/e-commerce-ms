package com.dekankilic.order.dto;

import java.math.BigDecimal;

public record PurchaseResponse(
        Integer id,
        String name,
        String description,
        double quantity,
        BigDecimal price
) {
}
