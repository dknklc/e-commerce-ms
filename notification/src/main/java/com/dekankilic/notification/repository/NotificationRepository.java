package com.dekankilic.notification.repository;

import com.dekankilic.notification.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {

}
