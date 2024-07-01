package com.dekankilic.payment.config.kafka;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class PaymentConfirmationTopicProperties {
    @Value("${kafka.topics.payment-confirmation.topicName}")
    private String topicName;
    @Value("${kafka.topics.payment-confirmation.partitionCount}")
    private int partitionCount;
    @Value("${kafka.topics.payment-confirmation.replicationFactor}")
    private int replicationFactor;
    @Value("${kafka.topics.payment-confirmation.retentionInMs}")
    private String retentionInMs;
}
