package com.dekankilic.order.config.kafka;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Setter
@Getter
public class OrderConfirmationTopicProperties {

    @Value("${kafka.topics.order-confirmation.topicName}")
    private String topicName;
    @Value("${kafka.topics.order-confirmation.partitionCount}")
    private int partitionCount;
    @Value("${kafka.topics.order-confirmation.replicationFactor}")
    private int replicationFactor;
    @Value("${kafka.topics.order-confirmation.retentionInMs}")
    private String retentionInMs;
}
