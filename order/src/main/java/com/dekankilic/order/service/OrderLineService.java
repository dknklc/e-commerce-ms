package com.dekankilic.order.service;

import com.dekankilic.order.dto.OrderLineRequest;
import com.dekankilic.order.mapper.OrderLineMapper;
import com.dekankilic.order.model.OrderLine;
import com.dekankilic.order.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineService {
    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper mapper;

    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        OrderLine orderLine = mapper.toOrderLine(orderLineRequest);
        return orderLineRepository.save(orderLine).getId();
    }
}
