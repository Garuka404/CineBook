package com.cbs.cinebook.service.impl;

import com.cbs.cinebook.dto.Reservation;
import com.cbs.cinebook.dto.request.ReservationRequestDTO;
import com.cbs.cinebook.dto.response.ReservationResponseDTO;
import com.cbs.cinebook.entity.*;
import com.cbs.cinebook.repositoty.*;
import com.cbs.cinebook.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;
    private  final CinemaRepository cinemaRepository;
    private final MovieRepository movieRepository;
    private final SeatRepository seatRepository;


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
    public ResponseEntity<ReservationResponseDTO> setReservation(ReservationRequestDTO reservationRequestDTO) {
        try{
            if(reservationRequestDTO==null){
                log.error("reservations is null for the add");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            CustomerEntity customerEntity = customerRepository.findById(reservationRequestDTO.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found"));

            CinemaEntity cinemaEntity=cinemaRepository.findById(reservationRequestDTO.getCinemaId())
                    .orElseThrow(() -> new RuntimeException("Cinema not found"));

            MovieEntity movieEntity=movieRepository.findById(reservationRequestDTO.getMovieId())
                    .orElseThrow(() -> new RuntimeException("Movie not found"));

            List<SeatEntity> seatEntities=seatRepository.findAllById(reservationRequestDTO.getSeatIds());

            if(seatEntities.size()!=reservationRequestDTO.getSeatIds().size()){
                log.error("Some seats not found");                        //check the all seat in the hall
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ReservationResponseDTO("Some seats not found",null));
            }
            for(SeatEntity seatEntity : seatEntities){
                if(!seatEntity.isAvailable()){
                    log.error("Seat {}{} not available",seatEntity.getRowLetter(),seatEntity.getNumber());
                    return  ResponseEntity.status(HttpStatus.CONFLICT)
                            .body(new ReservationResponseDTO("Seat "+seatEntity.getRowLetter()+seatEntity.getNumber()+" not available",null));
                }
            }
            ReservationEntity reservationEntity=new ReservationEntity();
            reservationEntity.setConNumber(reservationRequestDTO.getConNumber());
            reservationEntity.setDescription(reservationRequestDTO.getDescription());
            reservationEntity.setDate(reservationRequestDTO.getDate());
            reservationEntity.setTime(reservationRequestDTO.getTime());
            reservationEntity.setBookedBy(customerEntity);
            reservationEntity.setCinema(cinemaEntity);
            reservationEntity.setMovie(movieEntity);

            Set<SeatEntity> seats=new HashSet<>();
            for(SeatEntity seatEntity : seatEntities){
                seatEntity.setAvailable(false);
                seatEntity.setReservation(reservationEntity);
                seats.add(seatEntity);
            }
            reservationEntity.setSeats(seats);
            ReservationEntity savedReservation=reservationRepository.save(reservationEntity);
            log.info("Created reservation with id : {}",reservationEntity.getReservationId());

            Reservation reservation=new Reservation();
            reservation.setReservationId(savedReservation.getReservationId());
            reservation.setConNumber(savedReservation.getConNumber());
            reservation.setDescription(savedReservation.getDescription());
            reservation.setDate(savedReservation.getDate());
            reservation.setTime(savedReservation.getTime());
            reservation.setCustomer(savedReservation.getBookedBy());
            CinemaEntity cinema=savedReservation.getCinema();
            cinema.setBranch(savedReservation.getCinema().getBranch());
            reservation.setCinema(cinema);
            reservation.setMovie(savedReservation.getMovie());

             return ResponseEntity.status(HttpStatus.CREATED)
                     .body(new ReservationResponseDTO("Reservation made successfully",reservation));

        }catch (ResourceAccessException ex){
            log.error("Resource access exception: {}",ex.getMessage(),ex);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch (Exception e){
            log.error("Exception while finding user: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @Override
    public ResponseEntity<ReservationResponseDTO> deleteReservation(Long reservationId) {
        try{
            if(reservationId==null){
                log.warn("reservations is null for the delete");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            if(reservationRepository.existsById(reservationId)){
                reservationRepository.deleteById(reservationId);
                log.info("Deleted reservation with id: {}", reservationId);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ReservationResponseDTO("reservation "+reservationId+" delected Successfully ",null));
            }
            log.warn("No reservation with id: {} for the delete", reservationId);
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
                log.warn("reservationsTime is null for the get reservations");
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

    @Override
    public ResponseEntity<List<Reservation>> getReservationByDateAndTime(LocalDate reservationDate, LocalTime reservationTime) {
        try{
            if(reservationDate==null || reservationTime==null){
                log.warn("reservations Date or Time is null");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            List<ReservationEntity> reservationEntities=reservationRepository.findByDateAndTime(reservationDate, reservationTime);
            if(!reservationEntities.isEmpty()){
                List<Reservation> reservations=reservationEntities.stream()
                        .map(entity ->modelMapper.map(entity, Reservation.class)).toList();
                log.info("Found reservations with date: {} and time: {}", reservationDate, reservationTime);
                return ResponseEntity.status(HttpStatus.FOUND)
                        .body(reservations);
            }
            log.warn("No reservations with date: {} and time: {}", reservationDate, reservationTime);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (Exception e){
            log.error("Exception while finding user: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @Override
    public ResponseEntity<ReservationResponseDTO> cancelReservation(Long id) {
         try{
             ReservationEntity reservationEntity = reservationRepository.findById(id).orElseThrow(null);
             for(SeatEntity seat : reservationEntity.getSeats()){
                 seat.setAvailable(true);
                 seat.setReservation(null);
             }
             reservationRepository.delete(reservationEntity);
             log.info("Cancelled reservation with id: {}", id);
             return ResponseEntity.status(HttpStatus.OK)
                     .body(new ReservationResponseDTO("Reservation cancelled successfully",null));
         }catch (Exception e){
             log.error("Exception while finding user: {}", e.getMessage(), e);
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
         }
    }


}
