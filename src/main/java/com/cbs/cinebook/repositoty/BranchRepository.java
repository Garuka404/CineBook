package com.cbs.cinebook.repositoty;

import com.cbs.cinebook.entity.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<BranchEntity,Long> {
}
