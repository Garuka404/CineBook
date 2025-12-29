package com.cbs.cinebook.dto;

import com.cbs.cinebook.enums.SeatType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SeatDetail {
    @NotBlank
    private SeatType type;
    @NotNull
    @Positive
    private Double price;
    @NotNull
    @Positive
    private  Integer quantity;
}
