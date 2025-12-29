package com.cbs.cinebook.event;

import com.cbs.cinebook.dto.PaymentDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationCreatedEventProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishReservationCreated(PaymentDetails paymentDetails) {
        kafkaTemplate.send("reservation.pending",paymentDetails.getReservationId().toString(),paymentDetails);
        log.info("Event Published Successfully by reservationId={}",paymentDetails.getReservationId());
    }
}
