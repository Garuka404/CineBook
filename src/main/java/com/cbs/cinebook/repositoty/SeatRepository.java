package com.cbs.cinebook.repositoty;

import com.cbs.cinebook.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<SeatEntity, Long> {
    boolean existsByNumber(String number);

    boolean existsByRowLetter(String rowLetter);
}
