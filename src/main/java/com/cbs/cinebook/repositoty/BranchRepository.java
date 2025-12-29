package com.cbs.cinebook.repositoty;

import com.cbs.cinebook.entity.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<BranchEntity,Long> {
    boolean existsByContact(String contact);
}
