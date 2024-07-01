package com.dekankilic.order.mapper;

import com.dekankilic.order.dto.OrderRequest;
import com.dekankilic.order.dto.OrderResponse;
import com.dekankilic.order.model.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public Order toOrder(OrderRequest request){
        return Order.builder()
                .id(request.id())
                .reference(request.reference())
                .totalAmount(request.amount())
                .paymentMethod(request.paymentMethod())
                .customerId(request.customerId())
                .build();
    }

    public OrderResponse fromOrder(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .reference(order.getReference())
                .amount(order.getTotalAmount())
                .paymentMethod(order.getPaymentMethod())
                .customerId(order.getCustomerId())
                .build();
    }
}
