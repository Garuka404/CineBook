package com.cbs.cinebook.service;

import com.cbs.cinebook.dto.PaymentSessionCreatedEvent;
import com.cbs.cinebook.dto.Reservation;
import com.cbs.cinebook.dto.response.ReservationResponseDTO;
import com.cbs.cinebook.entity.ReservationEntity;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface ReservationService {
    ResponseEntity<List<Reservation>> getAllReservation();
    ResponseEntity<ReservationResponseDTO> getReservationById(UUID reservationId);
    ResponseEntity<ReservationResponseDTO> setReservation(Reservation reservationRequestDTO);
    ResponseEntity<ReservationResponseDTO> deleteReservation(UUID reservationId);
    ResponseEntity<ReservationResponseDTO> updateReservation(Reservation reservation);
    ResponseEntity<List<Reservation>> getReservationByDate(LocalDate reservationDate);
    ResponseEntity<List<Reservation>> getReservationByTime(LocalTime reservationTime);
    ResponseEntity<List<Reservation>> getReservationByDateAndTime(LocalDate reservationDate, LocalTime reservationTime);
    ResponseEntity<ReservationResponseDTO>  cancelReservation(UUID id);
    ReservationEntity updateSession(PaymentSessionCreatedEvent paymentSessionCreatedEvent);
    void confirmReservation(UUID reservationId);
    void failReservation(UUID reservationId);
}
