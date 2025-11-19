package com.cbs.cinebook.dto;

import com.cbs.cinebook.entity.CustomerEntity;
import lombok.*;
import java.sql.Time;
import java.util.Date;
import java.util.UUID;

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
    private Date date;
    private Time time;

}
