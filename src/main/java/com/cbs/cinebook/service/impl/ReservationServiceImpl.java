package com.cbs.cinebook.service.impl;

import com.cbs.cinebook.dto.Reservation;
import com.cbs.cinebook.dto.response.ReservationResponseDTO;
import com.cbs.cinebook.entity.CustomerEntity;
import com.cbs.cinebook.entity.ReservationEntity;
import com.cbs.cinebook.repositoty.CustomerRepository;
import com.cbs.cinebook.repositoty.ReservationRepository;
import com.cbs.cinebook.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;


    @Override
    public ResponseEntity<List<Reservation>> getAllReservation() {
        try{
          List<ReservationEntity> reservationEntities=reservationRepository.findAll();
          if(reservationEntities.isEmpty()){
              log.info("No reservations found");
              return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
          }
          List<Reservation> reservations=reservationEntities.stream()
                  .map(entity ->modelMapper.map(entity, Reservation.class))
                  .toList();
          log.info("Fetched all reservations successfully, count: {}", reservations.size());
          return ResponseEntity.ok(reservations);
        }catch (Exception e){
            log.error("Exception while finding user: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
     }

    @Override
    public ResponseEntity<ReservationResponseDTO> getReservationById(Long reservationId) {
        try{
            if(reservationId==null){
              log.warn("No reservations found");
              return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            if(reservationRepository.existsById(reservationId)){
                log.info("Found reservation with id: {}", reservationId);
                return ResponseEntity.status(HttpStatus.FOUND)
                        .body(new  ReservationResponseDTO("reservation ",modelMapper
                                .map(reservationRepository.findById(reservationId), Reservation.class)));
            }
            log.info("No reservation with id: {}", reservationId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ReservationResponseDTO("reservation with "+reservationId+" not exist ",null));

        }catch (Exception e){
            log.error("Exception while finding user: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<ReservationResponseDTO> setReservation(Reservation reservation) {
        try{
            if(reservation==null){
                log.error("reservations is null");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            if(!reservationRepository.existsById(reservation.getReservationId())){
                CustomerEntity madeBy=customerRepository.findByEmail(reservation.getCustomer().getEmail());
                ReservationEntity reservationEntity=modelMapper.map(reservation, ReservationEntity.class);
                reservation.setCustomer(madeBy);
                madeBy.getReservations().add(reservationEntity);
                reservationRepository.save(reservationEntity);
                log.info("Created reservation with id: {}", reservation.getReservationId());
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ReservationResponseDTO("Reservation made Successfully",reservation));
            }
            log.warn("reservation with id: {} already exist", reservation.getReservationId());
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ReservationResponseDTO("reservation already exist",null));
        }catch (Exception e){
            log.error("Exception while finding user: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<ReservationResponseDTO> deleteReservation(Long reservationId) {
        try{
            if(reservationId==null){
                log.warn("reservations is null");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            if(reservationRepository.existsById(reservationId)){
                reservationRepository.deleteById(reservationId);
                log.info("Deleted reservation with id: {}", reservationId);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ReservationResponseDTO("reservation "+reservationId+" delected Successfully ",null));
            }
            log.warn("No reservation with id: {}", reservationId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ReservationResponseDTO("reservation not found",null));
        }catch (Exception e){
            log.error("Exception while finding user: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<ReservationResponseDTO> updateReservation(Reservation reservation) {
        try{
            if(reservation==null){
                log.error("reservations is null");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            if(reservationRepository.existsById(reservation.getReservationId())){
                reservationRepository.save(modelMapper.map(reservation, ReservationEntity.class));
                log.info("Updated reservation with id: {}", reservation.getReservationId());
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ReservationResponseDTO("reservation "+reservation.getReservationId()+" updated Successfully ",reservation));
            }
            log.warn("No reservation with id: {}", reservation.getReservationId());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ReservationResponseDTO("reservation not found",null));

        }catch (Exception e){
            log.error("Exception while finding user: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<List<Reservation>> getReservationByDate(LocalDate reservationDate) {
        try{
            if(reservationDate==null){
                log.warn("reservationsDate is null");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            List<ReservationEntity>reservationEntities=reservationRepository.getReservationByDate(reservationDate);
            if(reservationEntities!=null){
                List<Reservation> reservations=reservationEntities.stream()
                                .map(entity ->modelMapper.map(entity, Reservation.class)).toList();
                log.info("Found reservations with date: {}", reservationDate);
                return ResponseEntity.status(HttpStatus.FOUND)
                        .body(reservations);
            }
            log.warn("No reservations with date: {}", reservationDate);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }catch (Exception e){
            log.error("Exception while finding user: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<List<Reservation>> getReservationByTime(LocalTime reservationTime) {
        try{
            if(reservationTime==null){
                log.warn("reservationsTime is null");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            List<ReservationEntity>reservationEntities=reservationRepository.getReservationByTime(reservationTime);
            if(reservationEntities!=null){
                List<Reservation> reservations=reservationEntities.stream()
                        .map(entity ->modelMapper.map(entity, Reservation.class)).toList();
                log.info("Found reservations with time: {}", reservationTime);
                return ResponseEntity.status(HttpStatus.FOUND)
                        .body(reservations);
            }
            log.warn("No reservations with time: {}", reservationTime);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }catch (Exception e){
            log.error("Exception while finding user: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
     }
}
