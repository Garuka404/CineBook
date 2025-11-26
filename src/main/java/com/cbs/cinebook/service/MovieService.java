package com.cbs.cinebook.service;

import com.cbs.cinebook.dto.Movie;
import com.cbs.cinebook.dto.response.MovieResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovieService {
    ResponseEntity<List<Movie>> findAllMovies();
    ResponseEntity<MovieResponseDTO> findMovieById(Long movieId);
    ResponseEntity<MovieResponseDTO> addMovie(Movie movie);
    ResponseEntity<MovieResponseDTO> updateMovie(Movie movie);
    ResponseEntity<MovieResponseDTO> deleteMovie(Long movieId);
    ResponseEntity<List<Movie>> findAllMoviesByYear(String year);
}
