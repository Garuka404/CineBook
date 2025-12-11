package com.cbs.cinebook.dto.request;

import com.cbs.cinebook.entity.BranchEntity;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CinemaRequestDTO {
    private Long id;
    private String name;
    private Long hallNumber;
    private String location;
    private String capacity;
    private Long branchId;

}
