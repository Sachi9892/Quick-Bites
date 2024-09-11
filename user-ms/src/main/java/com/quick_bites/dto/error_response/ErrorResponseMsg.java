package com.quick_bites.dto.error_response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponseMsg {

    private int status;
    private String message;
    private String path;

}