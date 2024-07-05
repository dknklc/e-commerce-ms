package com.dekankilic.notification.config.kafka;

import com.dekankilic.notification.dto.OrderConfirmation;
import com.dekankilic.notification.dto.PaymentConfirmation;
import com.dekankilic.notification.model.Notification;
import com.dekankilic.notification.model.enums.NotificationType;
import com.dekankilic.notification.repository.NotificationRepository;
import com.dekankilic.notification.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment.payment-confirmation.0", groupId = "notification-paymentGroup")
    public void consumePaymentConfirmationEvent(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info(String.format("Consuming the message from payment-topic Topic:: %s", paymentConfirmation));

        // SAVE notification
        notificationRepository.save(Notification.builder()
                        .type(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build());

        // TODO: send email

        String customerName = paymentConfirmation.customerFirstname() + " " + paymentConfirmation.customerLastname();
        emailService.sendPaymentConfirmationEmail(paymentConfirmation.customerEmail(), customerName, paymentConfirmation.amount(), paymentConfirmation.orderReference());
    }

    @KafkaListener(topics = "order.order-confirmation.0", groupId = "notification-orderGroup")
    public void consumeOrderConfirmationEvent(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info(String.format("Consuming the message from order-topic Topic:: %s", orderConfirmation));
        notificationRepository.save(Notification.builder()
                .type(NotificationType.ORDER_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .orderConfirmation(orderConfirmation)
                .build());

        // TODO: send email

        String customerName = orderConfirmation.customer().firstName() + " " + orderConfirmation.customer().lastName();
        emailService.sendOrderConfirmationEmail(orderConfirmation.customer().email(), customerName, orderConfirmation.totalAmount(), orderConfirmation.orderReference(), orderConfirmation.products());
    }


}
