package com.cbs.cinebook.repositoty;

import com.cbs.cinebook.dto.Seat;
import com.cbs.cinebook.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<SeatEntity, Long> {
}
