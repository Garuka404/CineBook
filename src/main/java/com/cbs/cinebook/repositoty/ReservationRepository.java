package com.cbs.cinebook.repositoty;

import com.cbs.cinebook.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
    List<ReservationEntity> getReservationByDate(LocalDate date);
    List<ReservationEntity> getReservationByTime(LocalTime time);
    List<ReservationEntity> findByDateAndTime(LocalDate date, LocalTime time);

}
