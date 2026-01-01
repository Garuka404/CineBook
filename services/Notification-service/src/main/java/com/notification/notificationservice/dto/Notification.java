package com.notification.notificationservice.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
public class Notification {
    private UUID reservationId;
    private LocalDate date;
    private LocalTime time;
    private String customerName;
    private String email;
    private String branchName;
    private Long cinemaId;
    private String  movieName;
    private List<Long> seatIds;

}

