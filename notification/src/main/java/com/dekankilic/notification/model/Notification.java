package com.dekankilic.notification.model;

import com.dekankilic.notification.dto.OrderConfirmation;
import com.dekankilic.notification.dto.PaymentConfirmation;
import com.dekankilic.notification.model.enums.NotificationType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {
    @Id
    private String id;

    private NotificationType type;
    private LocalDateTime notificationDate;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;
}
