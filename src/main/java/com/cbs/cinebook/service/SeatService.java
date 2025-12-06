package com.cbs.cinebook.service;

import com.cbs.cinebook.dto.Seat;
import com.cbs.cinebook.dto.response.SeatResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SeatService {
    ResponseEntity<SeatResponseDTO> addSeat(Seat seat);
    ResponseEntity<SeatResponseDTO> updateSeat(Seat seat);
    ResponseEntity<SeatResponseDTO> deleteSeat(Seat seat);
    ResponseEntity<List<Seat>>getSeats();
    ResponseEntity<List<Seat>> getSeatByRows(String row);
    ResponseEntity<SeatResponseDTO> getSeatById(Long id);

}
