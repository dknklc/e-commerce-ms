package com.dekankilic.payment.config.kafka;

import com.dekankilic.payment.dto.PaymentNotificationEvent;
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
    private final KafkaTemplate<String, PaymentNotificationEvent> kafkaTemplate;
    private final PaymentConfirmationTopicProperties paymentConfirmationTopicProperties;

    public void sendPaymentConfirmationMessage(PaymentNotificationEvent paymentNotificationEvent){
        log.info("Sending payment confirmation event with body <{}>", paymentNotificationEvent);
        Message<PaymentNotificationEvent> message = MessageBuilder.withPayload(paymentNotificationEvent).setHeader(KafkaHeaders.TOPIC, paymentConfirmationTopicProperties.getTopicName()).build();
        kafkaTemplate.send(message);
    }
}
