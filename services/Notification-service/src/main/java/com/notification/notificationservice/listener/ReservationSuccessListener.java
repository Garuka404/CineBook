package com.notification.notificationservice.listener;

import com.notification.notificationservice.dto.Notification;
import com.notification.notificationservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReservationSuccessListener {
    private final EmailService emailService;

    @KafkaListener(topics = "reservation.success",groupId = "notification-service-group2")
    public void onReservationCreated(Notification notification) {

        String email=notification.getEmail();
        String subject = "Reservation Success";
        String content = String.format(
                "Hello %s 👋%n%n" +
                        "🎉 Your cinema reservation has been *successfully confirmed!*%n%n" +

                        "🎟️ ==============================%n" +
                        "   RESERVATION DETAILS%n" +
                        "==============================%n" +
                        "🆔 Reservation ID : %s%n" +
                        "🏢 Branch         : %s%n" +
                        "📅 Date           : %s%n" +
                        "⏰ Time           : %s%n%n" +

                        "🎬 ==============================%n" +
                        "   MOVIE DETAILS%n" +
                        "==============================%n" +
                        "🏛️ Cinema ID      : %d%n" +
                        "🎞️ Movie Name       : %s%n%n" +

                        "💺 ==============================%n" +
                        "   SEAT DETAILS%n" +
                        "==============================%n" +
                        "🪑 Seats Reserved : %s%n%n" +

                        "📌 Please keep this email for your records.%n" +
                        "📞 Need help? Contact us anytime.%n%n" +

                        "🍿 Enjoy the show and have a great time!%n%n" +
                        "— Cinema Booking Team",
                notification.getCustomerName(),
                notification.getReservationId(),
                notification.getBranchName(),
                notification.getDate(),
                notification.getTime(),
                notification.getCinemaId(),
                notification.getMovieName(),
                notification.getSeatIds()
        );


        emailService.sendEmail(email, subject, content);
        log.info("Email sent successfully");

    }
}
