package com.cbs.cinebook.service;

import com.cbs.cinebook.dto.Reservation;
import com.cbs.cinebook.dto.request.ReservationRequestDTO;
import com.cbs.cinebook.dto.response.ReservationResponseDTO;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface ReservationService {
    ResponseEntity<List<Reservation>> getAllReservation();
    ResponseEntity<ReservationResponseDTO> getReservationById(Long reservationId);
    ResponseEntity<ReservationResponseDTO> setReservation(ReservationRequestDTO reservationRequestDTO);
    ResponseEntity<ReservationResponseDTO> deleteReservation(Long reservationId);
    ResponseEntity<ReservationResponseDTO> updateReservation(Reservation reservation);
    ResponseEntity<List<Reservation>> getReservationByDate(LocalDate reservationDate);
    ResponseEntity<List<Reservation>> getReservationByTime(LocalTime reservationTime);
    ResponseEntity<List<Reservation>> getReservationByDateAndTime(LocalDate reservationDate, LocalTime reservationTime);
    ResponseEntity<ReservationResponseDTO>  cancelReservation(Long id);


}
