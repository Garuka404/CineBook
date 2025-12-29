package com.cbs.cinebook.event;

import com.cbs.cinebook.dto.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationSuccessEventProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishReservationSuccess(Notification notification) {
        kafkaTemplate.send("reservation.success",notification.getReservationId().toString(),notification);
        log.info("Event Published Successfully by reservationId={}",notification.getReservationId());
    }

}
