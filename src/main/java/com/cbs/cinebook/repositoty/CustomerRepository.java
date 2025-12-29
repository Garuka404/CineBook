package com.cbs.cinebook.repositoty;

import com.cbs.cinebook.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    CustomerEntity findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<CustomerEntity> findByKeyClockId(String keyClockId);
}
