package com.dekankilic.notification.config.kafka;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class OrderConfirmationConsumerProperties {
    @Value("${kafka.topics.order-confirmation.topic}")
    private String topicName;
    @Value("${kafka.topics.order-confirmation.consumerGroup}")
    private String consumerGroup;
}
