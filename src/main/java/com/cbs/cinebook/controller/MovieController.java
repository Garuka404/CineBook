package com.cbs.cinebook.controller;

import com.cbs.cinebook.dto.Movie;
import com.cbs.cinebook.dto.response.MovieResponseDTO;
import com.cbs.cinebook.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/search-by-id/{id}")
    public ResponseEntity<MovieResponseDTO> getMovieById(@PathVariable Long id) {
        return movieService.findMovieById(id);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return movieService.findAllMovies();
    }
    @PostMapping("/add")
    public ResponseEntity<MovieResponseDTO> addMovie(@RequestBody Movie movie) {
        return movieService.addMovie(movie);
    }
    @PutMapping("/update")
    public ResponseEntity<MovieResponseDTO> updateMovie(@RequestBody Movie movie) {
        return movieService.updateMovie(movie);
    }
    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<MovieResponseDTO> deleteMovie(@PathVariable Long id) {
        return movieService.deleteMovie(id);
    }

}
