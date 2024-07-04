package com.dekankilic.payment.config.kafka;

import com.dekankilic.payment.dto.PaymentConfirmation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaPaymentConfirmationProducer {
    private final KafkaTemplate<String, PaymentConfirmation> kafkaTemplate;
    private final PaymentConfirmationTopicProperties paymentConfirmationTopicProperties;

    public void sendPaymentConfirmationMessage(PaymentConfirmation paymentConfirmation){
        log.info("Sending payment confirmation event with body <{}>", paymentConfirmation);
        Message<PaymentConfirmation> message = MessageBuilder.withPayload(paymentConfirmation).setHeader(KafkaHeaders.TOPIC, paymentConfirmationTopicProperties.getTopicName()).build();
        kafkaTemplate.send(message);
    }
}
