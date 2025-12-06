package com.cbs.cinebook.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "movie")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id", nullable = false)
    private Long id;
    @Column(name = "movie_name", nullable = false)
    private String name;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "show_date", nullable = false)
    private LocalDate showDate;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany(mappedBy = "movies", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<BranchEntity> branches=new HashSet<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<ReservationEntity> reservations=new HashSet<>();


}
