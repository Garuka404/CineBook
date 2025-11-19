package com.cbs.cinebook.service;

import com.cbs.cinebook.dto.Cinema;
import com.cbs.cinebook.dto.response.CinemaResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CinemaService {
    ResponseEntity<List<Cinema>> getAllCinemas();
    ResponseEntity<CinemaResponseDTO> getCinemasById(Long id);
    ResponseEntity<CinemaResponseDTO> deleteCinema(Long id);
    ResponseEntity<CinemaResponseDTO> updateCinema(Cinema cinema);
    ResponseEntity<CinemaResponseDTO> addCinema(Cinema cinema);

}
