package com.cbs.cinebook.dto;

import com.cbs.cinebook.entity.BranchEntity;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cinema {
    private Long id;
    private String name;
    private Long hallNumber;
    private String location;
    private String capacity;
    private BranchEntity branch;

}
