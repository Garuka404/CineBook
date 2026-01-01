package com.notification.notificationservice.listener;

import com.notification.notificationservice.dto.Notification;
import com.notification.notificationservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationFailedListener {
    private final EmailService emailService;

    @KafkaListener(topics = "reservation.Created",groupId = "notification-service-group2")
    public void onReservationCreated(Notification notification) {

        String email=notification.getEmail();
        String subject = "Notification Created";
        String content =
                "Hello " + notification.getCustomerName() + ",\n\n" +
                        "Your notification has been successfully created.\n" +
                        "Notification ID: " + notification.getReservationId() + "\n\n" +
                        "Thank you.";
        emailService.sendEmail(email, subject, content);

    }
}
