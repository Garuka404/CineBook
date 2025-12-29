package com.cbs.cinebook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentSessionCreatedEvent {
    private UUID reservationId;
    private String sessionId;
    private String sessionUrl;
}