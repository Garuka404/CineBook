package com.cbs.cinebook.service.impl;

import com.cbs.cinebook.dto.Branch;
import com.cbs.cinebook.dto.response.BranchResponseDTO;
import com.cbs.cinebook.entity.BranchEntity;
import com.cbs.cinebook.repositoty.BranchRepository;
import com.cbs.cinebook.service.BranchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<List<Branch>> getBranches() {
        try{
            List<BranchEntity> branchEntities=branchRepository.findAll();
            if(branchEntities.isEmpty()){
                log.warn("No branches found for the add for the cinema");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            List<Branch> branchEntityList=branchEntities.stream()
                    .map(entity->modelMapper.map(entity,Branch.class))
                    .toList();
            log.info("Branches list has been successfully retrieved");
            return ResponseEntity.status(HttpStatus.OK).body(branchEntityList);
        }catch (Exception ex){
            log.error("Exception while finding Branch list: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    @Override
    public  ResponseEntity<BranchResponseDTO> getBranch(Long branchId) {
        try {
            if (branchId == null) {
                log.warn("Branch id is null for the retrieve branches");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            if (branchRepository.existsById(branchId)) {
                log.info("Branch found with id {}", branchId);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new BranchResponseDTO("Branch found successfully", modelMapper
                                .map(branchRepository.findById(branchId), Branch.class)));
            }
            log.info("Branch not found with id {}", branchId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        } catch (Exception ex) {
            log.error("Exception while finding Branch: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public  ResponseEntity<BranchResponseDTO> addBranch(Branch branch) {
        try{
            if(branch==null){
                log.warn("Branch id is null for the add for the cinema");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            if(branchRepository.existsById(branch.getId())){
                log.warn("Branch already exists with id {}", branch.getId());
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            branchRepository.save(modelMapper.map(branch,BranchEntity.class));
            log.info("Branch has  been successfully added");
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new BranchResponseDTO("Branch "+branch.getId()+" has been Successfully added",branch));

        }catch (Exception ex){
            log.error("Exception while finding Branch: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public  ResponseEntity<BranchResponseDTO> updateBranch(Branch branch) {
        try {
            if(branch==null){
                log.warn("Branch id is null for the update for the cinema");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            if(branchRepository.existsById(branch.getId())){
                branchRepository.save(modelMapper.map(branch,BranchEntity.class));
                log.info("Branch found with id {} for the update", branch.getId());
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new BranchResponseDTO("Branch "+branch.getId()+"found successfully updated",branch));

            }
            log.info("Branch not found with id {} for the update", branch.getId());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new BranchResponseDTO("Branch "+branch.getId()+"not found for th update",null));

        }catch (Exception ex){
            log.error("Exception while finding Branch: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public  ResponseEntity<BranchResponseDTO> deleteBranch(Long branchId) {
        try{
            if(branchId==null){
            log.warn("Branch id is null for the delete for the cinema");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            if(branchRepository.existsById(branchId)){
                branchRepository.deleteById(branchId);
                log.info("Branch  with id {} is successfully deleted", branchId);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new BranchResponseDTO("Branch "+branchId+" has been successfully deleted",null));
            }
            log.info("Branch not found with id {} for the delete", branchId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new BranchResponseDTO("Branch "+branchId+"not found for th update",null));
        }catch (Exception ex){
            log.error("Exception while finding Branch: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
