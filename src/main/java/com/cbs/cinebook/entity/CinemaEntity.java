package com.cbs.cinebook.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "cinema")
public class CinemaEntity {
    @Id
    @Column(name = "hall_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hall_name", nullable = false,length = 50)
    private String name;

    @Column(name = "hall_number", nullable = false)
    private Long hallNumber;

    @Column(name = "hall_capacity")
    private Long capacity;

    @Column(name = "hall_location")
    private  String location;

    @OneToMany(mappedBy ="cinema",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<ReservationEntity> reservations=new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "branch_id")
    @JsonManagedReference
    private BranchEntity branch;

    @OneToMany(mappedBy = "cinema",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private Set<SeatEntity> seats=new HashSet<>();

}
