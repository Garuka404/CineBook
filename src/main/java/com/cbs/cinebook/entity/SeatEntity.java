package com.cbs.cinebook.entity;

import com.cbs.cinebook.enums.SeatType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "seat")
public class SeatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "seat_number", nullable = false)
    private String number;

    @Column(name = "seat_row_letter", nullable = false)
    private String rowLetter;

    @Column(name = "seat_type", nullable = false)
    private SeatType type;

    @Column(name = "isAvailable", nullable = false)
    private boolean isAvailable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recevation", nullable = false)
    @JsonBackReference
    private ReservationEntity reservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema", nullable = false)
    @JsonBackReference
    private CinemaEntity cinema;

}
