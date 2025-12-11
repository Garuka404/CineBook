package com.cbs.cinebook.repositoty;

import com.cbs.cinebook.entity.CinemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaRepository extends JpaRepository<CinemaEntity, Long> {
    CinemaEntity findByHallNumber(Long hallNumber);

}
