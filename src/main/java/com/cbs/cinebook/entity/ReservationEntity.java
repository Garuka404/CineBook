package com.cbs.cinebook.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "reservation")
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id", nullable = false, updatable = false)
    private Long reservationId;

    @Column(name = "con_number",length = 10, nullable = false)
    private String conNumber;

    @Column(name = "description")
    private String description;

    @Column(name = "reservation_date",nullable = false)
    private LocalDate date;

    @Column(name = "reservation_time",nullable = false)
    private LocalTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookedBy", nullable = false)
    @JsonBackReference
    private CustomerEntity bookedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_id",nullable = false)
    @JsonBackReference
    private CinemaEntity cinema;

}
