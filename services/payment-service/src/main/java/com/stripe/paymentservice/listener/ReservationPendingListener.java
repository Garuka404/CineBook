package com.stripe.paymentservice.listener;

import com.stripe.paymentservice.dto.PaymentRequest;
import com.stripe.paymentservice.dto.PaymentResponse;
import com.stripe.paymentservice.dto.PaymentSessionCreatedEvent;
import com.stripe.paymentservice.service.StripeService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationPendingListener {
    private final StripeService service;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(topics = "reservation.pending",groupId = "payment-service-group2")
    public void onReservationPending(PaymentRequest request){
          PaymentResponse paymentResponse= service.checkoutReservation(request);

          kafkaTemplate.send(
                  "payment.session.created",
                  PaymentSessionCreatedEvent.builder()
                          .reservationId(request.getReservationId())
                          .sessionId(paymentResponse.getSessionId())
                          .sessionUrl(paymentResponse.getSessionUrl())
                          .build()
          );
    }
}
