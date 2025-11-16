package com.cbs.cinebook.constant;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationConstant {
    private String errorMsg="is null";
    private String successMsg;
}
