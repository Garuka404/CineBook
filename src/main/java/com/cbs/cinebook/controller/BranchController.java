package com.cbs.cinebook.controller;

import com.cbs.cinebook.dto.Branch;
import com.cbs.cinebook.dto.response.BranchResponseDTO;
import com.cbs.cinebook.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/branch")
public class BranchController {
    private final BranchService branchService;

    @GetMapping
    public ResponseEntity<List<Branch>> getAllBranches() {
        return branchService.getBranches();
    }
    @PostMapping("/add")
    public ResponseEntity<BranchResponseDTO> createBranch(@RequestBody Branch branch) {
        return branchService.addBranch(branch);
    }
    @GetMapping("/search-by-id/{id}")
    public ResponseEntity<BranchResponseDTO> getBranchById(@PathVariable Long id) {
        return branchService.getBranch(id);
    }
    @PutMapping("/update")
    public ResponseEntity<BranchResponseDTO> updateBranch(@RequestBody Branch branch) {
        return branchService.updateBranch(branch);
    }
    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<BranchResponseDTO> deleteBranchById(@PathVariable Long id) {
        return branchService.deleteBranch(id);
    }
}
