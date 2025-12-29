package com.cbs.cinebook.listener;

import com.cbs.cinebook.dto.PaymentSessionCreatedEvent;
import com.cbs.cinebook.entity.ReservationEntity;
import com.cbs.cinebook.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentCreatedListener {

    private final ReservationService service;

    @KafkaListener(topics = "payment.session.created",groupId = "reservation-service-group")
    public void onPaymentCreated(PaymentSessionCreatedEvent paymentSessionCreatedEvent){
           ReservationEntity reservationEntity= service.updateSession(paymentSessionCreatedEvent);
    }
}
