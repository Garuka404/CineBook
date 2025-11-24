package com.cbs.cinebook.dto;

import com.cbs.cinebook.entity.CinemaEntity;
import com.cbs.cinebook.entity.CustomerEntity;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    private Long reservationId;
    private String conNumber;
    private String description;
    private CustomerEntity customer;
    private CinemaEntity cinema;
    private LocalDate date;
    private LocalTime time;

}
