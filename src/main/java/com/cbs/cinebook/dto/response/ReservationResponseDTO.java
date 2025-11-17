package com.cbs.cinebook.dto.response;

import com.cbs.cinebook.dto.Reservation;

import java.util.SequencedCollection;

public record ReservationResponseDTO(String message, Reservation reservation) {
}
