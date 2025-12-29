package com.cbs.cinebook.repositoty;

import com.cbs.cinebook.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
    List<ReservationEntity> getReservationByDate(LocalDate date);
    List<ReservationEntity> getReservationByTime(LocalTime time);
    List<ReservationEntity> findByDateAndTime(LocalDate date, LocalTime time);
    ReservationEntity findByReservationId(UUID uuid);

    boolean existsByReservationId(UUID reservationId);
    void deleteByReservationId(UUID reservationId);
}
