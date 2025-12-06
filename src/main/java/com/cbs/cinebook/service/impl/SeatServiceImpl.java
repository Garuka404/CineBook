package com.cbs.cinebook.service.impl;

import com.cbs.cinebook.dto.Seat;
import com.cbs.cinebook.dto.response.SeatResponseDTO;
import com.cbs.cinebook.entity.SeatEntity;
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
    @Override
    public ResponseEntity<SeatResponseDTO> addSeat(Seat seat) {
        try{
            if(seat==null){
                log.warn("seat is null for add seat");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            if(seatRepository.existsById(seat.getId())){
               log.warn("seat is already exist for add seat");
               return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            seatRepository.save(modelMapper.map(seat, SeatEntity.class));
            log.info("seat added successfully for add seat");
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new SeatResponseDTO("Seat successfully added",seat));

        }catch (Exception ex){
            log.error("Exception while adding Seat {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<SeatResponseDTO> updateSeat(Seat seat) {
        try{
            if(seat==null){
                log.warn("seat is null for update seat");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            if(seatRepository.existsById(seat.getId())){
                seatRepository.save(modelMapper.map(seat, SeatEntity.class));
                log.info("seat id {} updated successfully for update seat", seat.getId());
                return ResponseEntity.status(HttpStatus.FOUND)
                        .body(new SeatResponseDTO("Seat id "+seat.getId()+" successfully updated",seat));

            }
            log.warn("seat id {} not found for update seat", seat.getId());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (Exception ex){
            log.error("Exception while updating Seat {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<SeatResponseDTO> deleteSeat(Seat seat) {
        try{
            if(seat==null){
                log.warn("seat is null for delete seat");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            if(seatRepository.existsById(seat.getId())) {
                seatRepository.delete(modelMapper.map(seat, SeatEntity.class));
                log.info("seat id {} deleted successfully", seat.getId());
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new SeatResponseDTO("Seat id " + seat.getId() + " successfully deleted", null));
            }
            log.warn("seat id {} not found for delete seat", seat.getId());
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
                        .body(new ArrayList<Seat>());
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
