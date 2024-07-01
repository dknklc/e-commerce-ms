package com.dekankilic.payment.config.kafka;

import com.dekankilic.payment.dto.PaymentNotificationEvent;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfiguration {

    @Value("${kafka.host}")
    private String kafkaBrokers;

    @Bean
    public KafkaTemplate<String, PaymentNotificationEvent> kafkaTemplate() {
        return new KafkaTemplate<>(producerConfig());
    }

    private ProducerFactory<String, PaymentNotificationEvent> producerConfig() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBrokers);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        config.put(JsonSerializer.TYPE_MAPPINGS, "paymentNotificationEvent:com.dekankilic.payment.dto.PaymentNotificationEvent"); // When we send paymentNotificationEvent to consumer, it will aware of this object can be accepted.
        return new DefaultKafkaProducerFactory<>(config);
    }
}
