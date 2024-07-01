package com.dekankilic.order.mapper;

import com.dekankilic.order.dto.OrderLineRequest;
import com.dekankilic.order.dto.OrderLineResponse;
import com.dekankilic.order.model.Order;
import com.dekankilic.order.model.OrderLine;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
                .id(orderLineRequest.id())
                .order(Order.builder().id(orderLineRequest.orderId()).build())
                .productId(orderLineRequest.productId())
                .quantity(orderLineRequest.quantity())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return OrderLineResponse.builder()
                .id(orderLine.getId())
                .quantity(orderLine.getQuantity())
                .build();
    }
}
