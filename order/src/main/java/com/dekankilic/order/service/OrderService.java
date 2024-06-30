package com.dekankilic.order.service;

import com.dekankilic.order.dto.CustomerResponse;
import com.dekankilic.order.dto.OrderLineRequest;
import com.dekankilic.order.dto.OrderRequest;
import com.dekankilic.order.dto.PurchaseRequest;
import com.dekankilic.order.exception.BusinessException;
import com.dekankilic.order.mapper.OrderMapper;
import com.dekankilic.order.model.Order;
import com.dekankilic.order.repository.OrderRepository;
import com.dekankilic.order.service.client.CustomerFeignClient;
import com.dekankilic.order.service.client.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerFeignClient customerFeignClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;

    public Integer createOrder(OrderRequest request) {
        // check the customer --> customer microservice (OpenFeign)
        CustomerResponse customerResponse = customerFeignClient.findById(request.customerId()).orElseThrow(() -> new BusinessException("Cannot create order:: No customer exists with the provided ID"));

        // purchase the product --> product microservice (RestTemplate)
        productClient.purchaseProducts(request.products());

        // persist order
        Order order = orderRepository.save(mapper.toOrder(request));

        // persist order lines
        for(PurchaseRequest purchaseRequest : request.products()){
            orderLineService.saveOrderLine(new OrderLineRequest(null, order.getId(), purchaseRequest.productId(), purchaseRequest.quantity()));
        }

        // TODO: start payment process

        // send the order confirmation --> notification microservice (kafka)

        return null;
    }
}
