package com.cbs.cinebook.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String keyClockId;

    @Column(nullable = false,length = 50)
    private String name;

    @Column(nullable = false,length = 100)
    private String address;

    @Column(unique = true ,nullable = false,length = 100)
    private String email;

    @Column(nullable = false,length = 10)
    private String number;

    @Column(nullable = false,length = 10)
    private String age;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<ReservationEntity> reservations=new HashSet<>();

}
