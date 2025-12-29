package com.cbs.cinebook.dto.response;

import com.cbs.cinebook.dto.Reservation;

public record ReservationResponseDTO(String message, Reservation reservation) {
}
