package com.cbs.cinebook.dto.response;

import com.cbs.cinebook.dto.Cinema;
import com.cbs.cinebook.dto.request.CinemaRequestDTO;

public record CinemaResponseDTO(String message, Cinema cinema) {
}
