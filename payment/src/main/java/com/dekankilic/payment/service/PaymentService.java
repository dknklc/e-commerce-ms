package com.dekankilic.payment.service;

import com.dekankilic.payment.config.kafka.KafkaPaymentConfirmationProducer;
import com.dekankilic.payment.dto.PaymentConfirmation;
import com.dekankilic.payment.dto.PaymentRequest;
import com.dekankilic.payment.mapper.PaymentMapper;
import com.dekankilic.payment.model.Payment;
import com.dekankilic.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper mapper;
    private final KafkaPaymentConfirmationProducer producer;

    public Integer createPayment(PaymentRequest paymentRequest) {
        Payment payment = paymentRepository.save(mapper.toPayment(paymentRequest));

        producer.sendPaymentConfirmationMessage(
                new PaymentConfirmation(
                        paymentRequest.orderReference(),
                        paymentRequest.amount(),
                        paymentRequest.paymentMethod(),
                        paymentRequest.customer().firstName(),
                        paymentRequest.customer().lastName(),
                        paymentRequest.customer().email()
                )
        );
        return payment.getId();
    }
}
