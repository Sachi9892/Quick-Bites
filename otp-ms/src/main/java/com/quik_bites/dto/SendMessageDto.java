package com.quik_bites.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SendMessageDto {

    private String mobileNumber;
    private String message;

}
