package com.cbs.cinebook.dto.response;

import com.cbs.cinebook.dto.Reservation;
import com.cbs.cinebook.dto.request.ReservationRequestDTO;

import java.util.SequencedCollection;

public record ReservationResponseDTO(String message, Reservation reservation) {
}
