package com.stripe.paymentservice.dto;

import com.stripe.paymentservice.enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SeatDetail {
    private SeatType type;
    private Double price;
    private  Integer quantity;
}
