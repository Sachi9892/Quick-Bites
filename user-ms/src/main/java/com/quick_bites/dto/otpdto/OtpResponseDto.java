package com.quick_bites.dto.otpdto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class OtpResponseDto implements Serializable {

    private OtpStatus status;
    private String message;

}