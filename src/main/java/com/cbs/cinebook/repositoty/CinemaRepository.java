package com.cbs.cinebook.repositoty;

import com.cbs.cinebook.entity.CinemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends JpaRepository<CinemaEntity, Long> {
    CinemaEntity findByHallNumber(Long hallNumber);

    boolean existsByHallNumber(Long hallNumber);
}
