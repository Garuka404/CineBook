package com.cbs.cinebook.repositoty;

import com.cbs.cinebook.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    CustomerEntity findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByKeyClockId(String keycloakId);
}
