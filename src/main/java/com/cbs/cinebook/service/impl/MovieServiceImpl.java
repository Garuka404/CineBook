package com.cbs.cinebook.service.impl;

import com.cbs.cinebook.dto.Movie;
import com.cbs.cinebook.dto.response.MovieResponseDTO;
import com.cbs.cinebook.entity.BranchEntity;
import com.cbs.cinebook.entity.MovieEntity;
import com.cbs.cinebook.repositoty.BranchRepository;
import com.cbs.cinebook.repositoty.MovieRepository;
import com.cbs.cinebook.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final BranchRepository branchRepository;
    private final ModelMapper modelMapper;
    @Override
    public ResponseEntity<List<Movie>> findAllMovies() {
        try{
            List<MovieEntity> movieEntities = movieRepository.findAll();
            if(movieEntities.isEmpty()){
                log.warn("Movie list is empty for the get all movie");
                return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            List<Movie> movieList=movieEntities.stream()
                    .map(entity->modelMapper.map(entity,Movie.class))
                    .toList();
            log.info("Movie list has been successfully retrieved");
            return ResponseEntity.status(HttpStatus.OK).body(movieList);

        }catch (Exception ex){
            log.error("Exception while finding Branch list: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<MovieResponseDTO> findMovieById(Long movieId) {
        try{
            if(movieId==null){
                log.warn("Movie id is null for the get movie by id");
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            if(movieRepository.existsById(movieId)){
                log.info("Movie with id {} has been successfully retrieved", movieId);
                return ResponseEntity.status(HttpStatus.FOUND)
                        .body(new  MovieResponseDTO("found movie ",modelMapper.map(movieRepository.findById(movieId),Movie.class)));
            }
            log.warn("Movie with id {} not found for the get by id", movieId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (Exception ex){
            log.error("Exception while finding Branch list: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @Override
    public ResponseEntity<MovieResponseDTO> addMovie(Movie movie) {
        try{
            if(movie==null){
                log.warn("Movie is null for the add movie");
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            if(movieRepository.existsById(movie.getId())){
                log.warn("Movie with id {}  already  added", movie.getId());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            BranchEntity branchEntity=modelMapper.map(branchRepository.findById(movie.getBranchId()),BranchEntity.class);
            MovieEntity movieEntity=modelMapper.map(movie,MovieEntity.class);
            movieEntity.getBranches().add(branchEntity);
            log.info("Movie with id {} has been successfully added", movie.getId());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new MovieResponseDTO("movie Successfully added ",modelMapper.map(movieRepository.save(movieEntity),Movie.class)));
        }catch (Exception ex){
            log.error("Exception while adding movie {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<MovieResponseDTO> updateMovie(Movie movie) {
        try{
            if(movie==null){
                log.warn("Movie is null for the update movie");
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            if(movieRepository.existsById(movie.getId())){
                movieRepository.save(modelMapper.map(movie,MovieEntity.class));
                log.info("Movie with id {} has been successfully updated", movie.getId());
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new MovieResponseDTO("Movie with id"+movie.getId()+"updated successfully",movie));
            }
            log.warn("Movie with id {} not found for the update movie", movie.getId());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        } catch (Exception e) {
            log.error("Exception while updating movie {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
         }
    }

    @Override
    public ResponseEntity<MovieResponseDTO> deleteMovie(Long movieId) {
        return null;
    }

    @Override
    public ResponseEntity<List<Movie> >findAllMoviesByYear(String year) {
        return null;
    }
}
