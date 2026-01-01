package com.stripe.paymentservice.controller;

import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.paymentservice.dto.PaymentFailedEvent;
import com.stripe.paymentservice.dto.PaymentSuccessEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/stripe/webhook")
@RequiredArgsConstructor
public class PaymentController {
    @Value("${stripe.webhookSecret}")
     private String webhookSecret;

    private final KafkaTemplate<String,Object>kafkaTemplate;

    @PostMapping
    public ResponseEntity<String> handleWebhook(
            @RequestBody String payload,
            @RequestHeader("Stripe-Signature") String sigHeader

    ){
        Event event;

        try {
            event = Webhook.constructEvent(
                    payload,
                    sigHeader,
                    webhookSecret
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid signature");
        }
        switch (event.getType()) {

            case "checkout.session.completed" -> {
                Session session = (Session) event.getDataObjectDeserializer()
                        .getObject().orElseThrow();

                String reservationIdStr = session.getMetadata().get("reservationId");

                if (reservationIdStr == null || reservationIdStr.isBlank()) {
                    throw new IllegalStateException("Missing reservationId in Stripe metadata");
                }
                UUID reservationId = UUID.fromString(reservationIdStr);

                kafkaTemplate.send(
                        "payment.success",
                        new PaymentSuccessEvent(
                                reservationId,
                                session.getPaymentIntent(),
                                session.getAmountTotal(),
                                session.getCurrency()
                        )
                );
            }

            case "payment_intent.payment_failed",
                 "checkout.session.async_payment_failed" -> {

                PaymentIntent intent = (PaymentIntent) event
                        .getDataObjectDeserializer()
                        .getObject().orElseThrow();

                String reservationIdStr = intent.getMetadata().get("reservationId");

                UUID reservationId = UUID.fromString(reservationIdStr);

                kafkaTemplate.send(
                        "payment.failed",
                        new PaymentFailedEvent(
                                reservationId,
                                intent.getId(),
                                intent.getLastPaymentError() != null
                                        ? intent.getLastPaymentError().getMessage()
                                        : "PAYMENT_FAILED"
                        )
                );
            }
        }
       return  ResponseEntity.ok("webhook processed");
}}
