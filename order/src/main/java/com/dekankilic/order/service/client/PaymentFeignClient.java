package com.dekankilic.order.service.client;

import com.dekankilic.order.dto.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment", url = "${application.config.payment-url}")
public interface PaymentFeignClient {

    @PostMapping
    Integer createPayment(@RequestBody PaymentRequest paymentRequest);
}
