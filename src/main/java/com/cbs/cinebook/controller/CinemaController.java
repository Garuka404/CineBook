package com.cbs.cinebook.controller;

import com.cbs.cinebook.dto.Cinema;
import com.cbs.cinebook.dto.request.CinemaRequestDTO;
import com.cbs.cinebook.dto.response.CinemaResponseDTO;
import com.cbs.cinebook.service.CinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cinema")
@RequiredArgsConstructor
public class CinemaController {
    private final CinemaService cinemaService;

    @PostMapping
    private ResponseEntity<CinemaResponseDTO> addCinema(@RequestBody CinemaRequestDTO cinema) {
        return cinemaService.addCinema(cinema);
    }
    @GetMapping
    public ResponseEntity<List<Cinema>> getAllCinemas() {
        return cinemaService.getAllCinemas();
    }
    @GetMapping("/{id}")
    public ResponseEntity<CinemaResponseDTO> getCinemaById(@PathVariable Long id) {
        return cinemaService.getCinemasById(id);
    }
    @PutMapping
    public ResponseEntity<CinemaResponseDTO> updateCinema(@RequestBody CinemaRequestDTO cinema) {
        return cinemaService.updateCinema(cinema);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<CinemaResponseDTO> deleteCinema(@PathVariable Long id) {
        return cinemaService.deleteCinema(id);
    }
}
