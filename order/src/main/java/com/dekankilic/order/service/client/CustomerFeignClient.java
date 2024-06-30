package com.dekankilic.order.service.client;

import com.dekankilic.order.dto.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "customer", url = "${application.config.customer-url}")
public interface CustomerFeignClient {

    @GetMapping("/{id}")
    Optional<CustomerResponse> findById(@PathVariable String id);
}
