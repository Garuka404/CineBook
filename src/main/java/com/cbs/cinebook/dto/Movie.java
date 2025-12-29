package com.cbs.cinebook.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Movie {
    private Long id;
    private String name;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate showDate;
    private String description;
    private Long branchId;



}
