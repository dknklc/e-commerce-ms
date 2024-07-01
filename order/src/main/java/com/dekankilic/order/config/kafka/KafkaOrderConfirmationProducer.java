package com.dekankilic.order.config.kafka;

import com.dekankilic.order.dto.OrderConfirmation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaOrderConfirmationProducer {
    private final OrderConfirmationTopicProperties orderConfirmationTopicProperties;
    private final KafkaTemplate<String, OrderConfirmation> kafkaTemplate;

    public void sendOrderConfirmationMessage(OrderConfirmation orderConfirmation){
        log.info("Sending order confirmation");
        Message<OrderConfirmation> message = MessageBuilder.withPayload(orderConfirmation).setHeader(KafkaHeaders.TOPIC, orderConfirmationTopicProperties.getTopicName()).build();
        kafkaTemplate.send(message);
    }
}
