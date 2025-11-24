package com.cbs.cinebook.service.impl;

import com.cbs.cinebook.dto.Cinema;
import com.cbs.cinebook.dto.response.CinemaResponseDTO;
import com.cbs.cinebook.entity.BranchEntity;
import com.cbs.cinebook.entity.CinemaEntity;
import com.cbs.cinebook.repositoty.CinemaRepository;
import com.cbs.cinebook.service.CinemaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CinemaServiceImpl implements CinemaService {

    private final CinemaRepository cinemaRepository;
    private final ModelMapper modelMapper;


    @Override
    public ResponseEntity<List<Cinema>> getAllCinemas() {
        try{
            List<CinemaEntity>cinemaEntities = cinemaRepository.findAll();
            if(cinemaEntities.isEmpty()){
                log.warn("Cinema list is empty for the get all cinemas");
                return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            List<Cinema> cinemas=cinemaEntities.stream()
                    .map(entity->modelMapper.map(entity, Cinema.class))
                    .toList();
            log.info("Cinema list has been successfully retrieved");
            return ResponseEntity.status(HttpStatus.OK).body(cinemas);
        }catch(Exception ex){
            log.error("Exception while finding Cinemas: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
     }

    @Override
    public ResponseEntity<CinemaResponseDTO> getCinemasById(Long id) {
        try{
            if(id == null){
                log.warn("Cinema id is null for the get for the cinema");
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            if(cinemaRepository.existsById(id)){
                Optional<CinemaEntity> cinemaEntity = cinemaRepository.findById(id);
                log.info("Cinema entity has been successfully retrieved");
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new CinemaResponseDTO("Cinema is id "+id,modelMapper.map(cinemaEntity, Cinema.class)));
            }
            log.warn("Cinema id {} not found for the get for the cinema", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch(Exception ex){
            log.error("Exception while finding Cinema by ID: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<CinemaResponseDTO> deleteCinema(Long id) {
        try {
            if (id == null) {
                log.warn("Cinema id is null for the delete for the cinema");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            if (cinemaRepository.existsById(id)) {
                cinemaRepository.deleteById(id);
                log.info("Cinema {} entity has been successfully deleted", id);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new CinemaResponseDTO("Cinema " + id + "entity has been successfully deleted ", null));
            }
            log.info("Cinema id {} not found for the delete for the cinema", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CinemaResponseDTO("Cinema id "+id+" not found for the delete for the cinema", null));
        }catch(Exception ex){
            log.error("Exception while finding Cinema by ID: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<CinemaResponseDTO> updateCinema(Cinema cinema) {
        try {
            if (cinema == null) {
                log.warn("Cinema is null for the update for the cinema");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            if (cinemaRepository.existsById(cinema.getId())) {
                cinemaRepository.save(modelMapper.map(cinema, CinemaEntity.class));
                log.info("Cinema entity has been successfully updated");
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new CinemaResponseDTO("Cinema id " + cinema.getId() + "updated successfully ", null));
            }
            log.info("Cinema id {} not found for the update", cinema.getId());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CinemaResponseDTO("Cinema id "+cinema.getId()+" not found for the update", null));
        }catch(Exception ex){
            log.error("Exception while finding Cinema by ID: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<CinemaResponseDTO> addCinema(Cinema cinema) {
        try{
            if(cinema == null) {
                log.warn("Cinema is null for the add for the cinema");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            if (!cinemaRepository.existsById(cinema.getId())) {
                BranchEntity  branch=modelMapper.map(cinema.getBranch().getId(), BranchEntity.class);
                CinemaEntity cinemaEntity=modelMapper.map(cinema,CinemaEntity.class);
                cinema.setBranch(branch);
                branch.getCinemas().add(cinemaEntity);
                cinemaRepository.save(cinemaEntity);
                log.info("Cinema entity has been successfully added");
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new CinemaResponseDTO("Cinema id " + cinema.getId() + "added successfully ",cinema));
            }
            log.warn("Cinema entity already exists for the add for the cinema");
            return ResponseEntity.status(HttpStatus.CONFLICT).build();


        }catch(Exception ex){
            log.error("Exception while finding Cinema by ID: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
