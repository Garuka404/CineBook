package com.cbs.cinebook.dto;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private String name;
    private String email;
    private String address;
    private String contactNo;
    private String age;

}
