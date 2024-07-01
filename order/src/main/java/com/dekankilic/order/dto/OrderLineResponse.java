package com.dekankilic.order.dto;

import lombok.Builder;

@Builder
public record OrderLineResponse(
        Integer id,
        double quantity
) {
}
