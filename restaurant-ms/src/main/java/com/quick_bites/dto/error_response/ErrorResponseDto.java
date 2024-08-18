package com.quick_bites.dto.error_response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {

    private String apiPath;

    private HttpStatus status;

    private String errorMsg;

    private LocalDateTime time;

}
