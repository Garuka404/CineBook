package com.cbs.cinebook.dto;

import com.cbs.cinebook.enums.SeatType;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Seat {

    private Long id;
    private String number;
    private String rowLetter;
    private SeatType type;
    private Double price;
    private boolean isAvailable;
    private Cinema cinema;
    private Reservation reservation;

}
