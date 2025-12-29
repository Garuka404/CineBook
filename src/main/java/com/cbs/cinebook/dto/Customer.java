package com.cbs.cinebook.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private Long id;
    private String keyClockId;
    private String name;
    private String email;
    private String address;
    private String number;
    private String age;

}
