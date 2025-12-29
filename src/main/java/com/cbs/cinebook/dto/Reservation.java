package com.cbs.cinebook.dto;

import com.cbs.cinebook.entity.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    private UUID reservationId;
    @Size(min = 10, max = 10)
    private String conNumber;
    private String description;
    private LocalDate date;
    private LocalTime time;
    private Long customerId;
    private Long cinemaId;
    private Long  movieId;
    private List<Long> seatIds;


}
