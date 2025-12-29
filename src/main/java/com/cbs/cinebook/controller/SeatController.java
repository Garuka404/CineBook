package com.cbs.cinebook.controller;

import com.cbs.cinebook.dto.Seat;
import com.cbs.cinebook.dto.response.SeatResponseDTO;
import com.cbs.cinebook.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seat")
@RequiredArgsConstructor
public class SeatController {
    private final SeatService seatService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SeatResponseDTO> addSeat(@RequestBody Seat seat){
       return seatService.addSeat(seat);

    }
    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SeatResponseDTO> updateSeat(@RequestBody Seat seat){
        return seatService.updateSeat(seat);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SeatResponseDTO> deleteSeat(@PathVariable Long id){
        return seatService.deleteSeat(id);
    }
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Seat>> seats(){
        return  seatService.getSeats();
    }
}
