package com.cbs.cinebook.listener;

import com.cbs.cinebook.dto.PaymentFailedEvent;
import com.cbs.cinebook.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentFailedListener {

    private final ReservationService reservationService;
    @KafkaListener(topics = "payment.failed")
    public void onPaymentFailed(PaymentFailedEvent event) {
        reservationService.failReservation(event.getReservationId());
    }

}
