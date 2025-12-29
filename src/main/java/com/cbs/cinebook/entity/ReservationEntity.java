package com.cbs.cinebook.entity;

import com.cbs.cinebook.enums.ReservationStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "reservation")
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "reservation_id", nullable = false, updatable = false)
    private UUID reservationId;

    @Column(name = "con_number",length = 10, nullable = false)
    private String conNumber;

    @Column(name = "description")
    private String description;

    @Column(name = "reservation_date",nullable = false)
    private LocalDate date;

    @Column(name = "reservation_time",nullable = false)
    private LocalTime time;

    @Column(name = "status",nullable = false)
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "session_url",length = 2000)
    private String sessionUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookedBy", nullable = false)
    @JsonBackReference
    private CustomerEntity customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_id",nullable = false)
    @JsonBackReference
    private CinemaEntity cinema;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    @JsonBackReference
    private MovieEntity movie;

    @ManyToMany(mappedBy = "reservation",fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<SeatEntity> seats=new HashSet<>();

}
