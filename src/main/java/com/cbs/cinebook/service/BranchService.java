package com.cbs.cinebook.service;

import com.cbs.cinebook.dto.Branch;
import com.cbs.cinebook.dto.response.BranchResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BranchService {
      ResponseEntity<List<Branch>> getBranches();
    ResponseEntity<BranchResponseDTO> getBranch(Long branchId);
    ResponseEntity<BranchResponseDTO> addBranch(Branch branch);
    ResponseEntity<BranchResponseDTO> updateBranch(Branch branch);
    ResponseEntity<BranchResponseDTO> deleteBranch(Long branchId);
}
