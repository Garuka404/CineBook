package com.stripe.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private UUID reservationId;
    private String customerName;
    private String email;
    private String currency;
    private List<SeatDetail> seats;

}
