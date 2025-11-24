package com.cbs.cinebook.dto;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Branch {
    private  Long id;
    private  String name;
    private  String location;
    private  String contact;
}
