package com.cbs.cinebook.controller;

import com.cbs.cinebook.dto.Branch;
import com.cbs.cinebook.dto.response.BranchResponseDTO;
import com.cbs.cinebook.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/branch")
public class BranchController {
    private final BranchService branchService;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<Branch>> getAllBranches() {
        return branchService.getBranches();
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BranchResponseDTO> createBranch(@RequestBody Branch branch) {
        return branchService.addBranch(branch);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<BranchResponseDTO> getBranchById(@PathVariable Long id) {
        return branchService.getBranch(id);
    }
    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BranchResponseDTO> updateBranch(@RequestBody Branch branch) {
        return branchService.updateBranch(branch);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BranchResponseDTO> deleteBranchById(@PathVariable Long id) {
        return branchService.deleteBranch(id);
    }
}
