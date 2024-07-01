package com.dekankilic.order.repository;

import com.dekankilic.order.dto.OrderLineResponse;
import com.dekankilic.order.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
    List<OrderLine> findAllByOrderId(Integer orderId);
}
