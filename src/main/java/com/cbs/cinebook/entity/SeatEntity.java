package com.cbs.cinebook.entity;

import com.cbs.cinebook.enums.SeatType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

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
    @Enumerated(EnumType.STRING)
    private SeatType type;

    @Column(name = "available", nullable = false)
    private boolean available;

    @Column(name = "price",nullable = false)
    private Double price;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="seat_reservation",
     joinColumns = @JoinColumn(name="seat_id"),
            inverseJoinColumns = @JoinColumn(name = "reservation_id")
    )
    @JsonManagedReference
    private Set<ReservationEntity> reservation=new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema", nullable = false)
    @JsonBackReference
    private CinemaEntity cinema;

}
