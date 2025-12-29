package com.cbs.cinebook.service.impl;

import com.cbs.cinebook.dto.Seat;
import com.cbs.cinebook.dto.response.SeatResponseDTO;
import com.cbs.cinebook.entity.SeatEntity;
import com.cbs.cinebook.repositoty.CinemaRepository;
import com.cbs.cinebook.repositoty.SeatRepository;
import com.cbs.cinebook.service.SeatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;
    private final ModelMapper modelMapper;
    private final CinemaRepository cinemaRepository;
    @Override
    public ResponseEntity<SeatResponseDTO> addSeat(Seat seatRequest) {
        try{
            if(seatRequest==null){
                log.warn("seat is null for add seat");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            if(seatRepository.existsByNumber(seatRequest.getNumber()) &&
                    seatRepository.existsByRowLetter(seatRequest.getRowLetter())){
               log.warn("seat is already exist for add seat");
               return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            SeatEntity seatEntity=modelMapper.map(seatRequest,SeatEntity.class);
            seatEntity.setCinema(cinemaRepository.findById(seatRequest.getCinemaId()).orElse(null));

            SeatEntity saved= seatRepository.save(seatEntity);
            Seat seat=modelMapper.map(saved,Seat.class);
            seat.setCinemaId(saved.getId());
            log.info("seat added successfully for add seat");
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new SeatResponseDTO("Seat successfully added",seat));

        }catch (Exception ex){
            log.error("Exception while adding Seat {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<SeatResponseDTO> updateSeat(Seat seatRequest) {
        try{
            if(seatRequest==null){
                log.warn("seatRequest is null for update seatRequest");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            if(seatRepository.existsById(seatRequest.getId())){
              SeatEntity seatEntity=seatRepository.save(modelMapper.map(seatRequest, SeatEntity.class));
              Seat seat=modelMapper.map(seatEntity,Seat.class);
              seat.setCinemaId(seat.getCinemaId());
                log.info("seatRequest id {} updated successfully for update seatRequest", seatRequest.getId());
                return ResponseEntity.status(HttpStatus.FOUND)
                        .body(new SeatResponseDTO("Seat id "+seatRequest.getId()+" successfully updated",seat));

            }
            log.warn("seatRequest id {} not found for update seatRequest", seatRequest.getId());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (Exception ex){
            log.error("Exception while updating Seat {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<SeatResponseDTO> deleteSeat(Long id){
        try{
            if(id==null){
                log.warn("seat is null for delete seat");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            if(seatRepository.existsById(id)) {
                SeatEntity seat=seatRepository.findById(id).orElse(null);
                seatRepository.delete(modelMapper.map(seat, SeatEntity.class));
                log.info("seat id {} deleted successfully",id);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new SeatResponseDTO("Seat id " + id + " successfully deleted", null));
            }
            log.warn("seat id {} not found for delete seat",id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (Exception ex){
            log.error("Exception while deleting Seat {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<List<Seat>> getSeats() {
        try{
            List<SeatEntity> seatEntities = seatRepository.findAll();
            if(seatEntities.isEmpty()){
                log.warn("seats is null for get seats");
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body(new ArrayList<>());
            }
            List<Seat> seats = seatEntities.stream()
                    .map(entity->modelMapper.map(entity,Seat.class))
                    .toList();
            log.info("get seats successfully for get seats");
            return  ResponseEntity.status(HttpStatus.OK)
                    .body(seats);
        }catch (Exception ex){
            log.error("Exception while getting seats", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<List<Seat>> getSeatByRows(String row) {
        return null;
    }

    @Override
    public ResponseEntity<SeatResponseDTO> getSeatById(Long id) {
        return null;
    }
}
