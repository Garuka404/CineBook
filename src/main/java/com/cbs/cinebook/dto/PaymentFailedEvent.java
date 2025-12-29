package com.cbs.cinebook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentFailedEvent {
    private UUID reservationId;
    private String paymentIntentId;
    private String reason;
}
