package com.cbs.cinebook.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(nullable = false,length = 50)
    private String name;

    @Column(nullable = false,length = 100)
    private String address;

    @Column(unique = true ,nullable = false,length = 100)
    private String email;

    @Column(nullable = false,length = 10)
    private String contactNo;

    @Column(nullable = false,length = 10)
    private String age;

}
