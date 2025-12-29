package com.cbs.cinebook.listener;

import com.cbs.cinebook.dto.PaymentSuccessEvent;
import com.cbs.cinebook.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentSuccessListener {

    private final ReservationService reservationService;
    @KafkaListener(topics = "payment.success")
    public void onPaymentSuccess(PaymentSuccessEvent event) {
        reservationService.confirmReservation(event.getReservationId());
    }

}
