package com.quick_bites.dto.otpdto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OtpResponseDto {

    private OtpStatus status;
    private String message;

}