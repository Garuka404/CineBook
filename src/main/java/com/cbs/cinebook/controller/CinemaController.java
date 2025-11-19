package com.cbs.cinebook.controller;

import com.cbs.cinebook.dto.Cinema;
import com.cbs.cinebook.dto.response.CinemaResponseDTO;
import com.cbs.cinebook.service.CinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinema")
@RequiredArgsConstructor
public class CinemaController {
    private final CinemaService cinemaService;

    @PostMapping("/add")
    private ResponseEntity<CinemaResponseDTO> addCinema(@RequestBody Cinema cinema) {
        return cinemaService.addCinema(cinema);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Cinema>> getAllCinemas() {
        return cinemaService.getAllCinemas();
    }
    @GetMapping("/search-by-id")
    public ResponseEntity<CinemaResponseDTO> getCinemaById(@RequestParam Long id) {
        return cinemaService.getCinemasById(id);
    }
    @PutMapping("/update")
    public ResponseEntity<CinemaResponseDTO> updateCinema(@RequestBody Cinema cinema) {
        return cinemaService.updateCinema(cinema);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<CinemaResponseDTO> deleteCinema(@RequestParam Long id) {
        return cinemaService.deleteCinema(id);
    }
}
