package com.quick_bites.dto.paymentdto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerificationApiResponse {

    private String message;
    private boolean success;

}
