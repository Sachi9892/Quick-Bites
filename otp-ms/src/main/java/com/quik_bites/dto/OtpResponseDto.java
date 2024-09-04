package com.quik_bites.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OtpResponseDto {

    private OtpStatus status;
    private String message;

}
