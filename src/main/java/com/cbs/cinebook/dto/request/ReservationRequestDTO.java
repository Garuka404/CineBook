package com.cbs.cinebook.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequestDTO {
    private Long reservationId;
    @Size(min = 1, max = 50)
    private String conNumber;
    private String description;
    private LocalDate date;
    private LocalTime time;
    private Long customerId;
    private Long cinemaId;
    private Long  movieId;
    private List<Long> seatIds;

}
