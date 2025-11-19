package com.cbs.cinebook.controller;

import com.cbs.cinebook.dto.Reservation;
import com.cbs.cinebook.dto.response.ReservationResponseDTO;
import com.cbs.cinebook.entity.ReservationEntity;
import com.cbs.cinebook.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/add")
    public ResponseEntity<ReservationResponseDTO> addReservation(@RequestBody Reservation reservation) {
        return reservationService.setReservation(reservation);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return reservationService.getAllReservation();
    }
    @GetMapping("/search-by-email/{id}")
    public ResponseEntity<ReservationResponseDTO> getReservationsById(@PathVariable Long id) {
        return reservationService.getReservationById(id);
    }
    @GetMapping("/search-by-date")
    public ResponseEntity<List<Reservation>> getReservationsByDate(@RequestParam LocalDate date) {
        return reservationService.getReservationByDate(date);
    }
    @GetMapping("/search-by-time")
    public ResponseEntity<List<Reservation>> getReservationsByTime(@RequestParam LocalTime time) {
        return reservationService.getReservationByTime(time);
    }
    @PutMapping("/update")
    public ResponseEntity<ReservationResponseDTO> updateReservation(@RequestBody Reservation reservation) {
        return reservationService.updateReservation(reservation);
    }
    @DeleteMapping("/delete-by-id")
    public ResponseEntity<ReservationResponseDTO> deleteReservationById(@RequestParam(name = "reservationId") Long reservationId) {
        return reservationService.deleteReservation(reservationId);
    }
    @GetMapping("/search-by-date-and-time")
    public ResponseEntity<List<Reservation>> getReservationsByDateAndTime(@RequestParam(name = "reservationDate") LocalDate date, @RequestParam(name = "reservationTime") LocalTime time){
        return reservationService.getReservationByDateAndTime(date, time);
    }

}
