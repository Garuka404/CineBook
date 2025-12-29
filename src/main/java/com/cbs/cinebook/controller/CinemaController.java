package com.cbs.cinebook.controller;

import com.cbs.cinebook.dto.Cinema;
import com.cbs.cinebook.dto.response.CinemaResponseDTO;
import com.cbs.cinebook.service.CinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cinema")
@RequiredArgsConstructor
public class CinemaController {
    private final CinemaService cinemaService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CinemaResponseDTO> addCinema(@RequestBody Cinema cinema) {
        return cinemaService.addCinema(cinema);
    }
    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<Cinema>> getAllCinemas() {
        return cinemaService.getAllCinemas();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<CinemaResponseDTO> getCinemaById(@PathVariable Long id) {
        return cinemaService.getCinemasById(id);
    }
    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CinemaResponseDTO> updateCinema(@RequestBody Cinema cinema) {
        return cinemaService.updateCinema(cinema);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CinemaResponseDTO> deleteCinema(@PathVariable Long id) {
        return cinemaService.deleteCinema(id);
    }
}
