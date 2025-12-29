package com.cbs.cinebook.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    private UUID reservationId;
    @Size(min = 10, max = 10)
    private LocalDate date;
    private LocalTime time;
    private String customerName;
    private String email;
    private String branchName;
    private Long cinemaId;
    private Long  movieId;
    private List<Long> seatIds;
}
