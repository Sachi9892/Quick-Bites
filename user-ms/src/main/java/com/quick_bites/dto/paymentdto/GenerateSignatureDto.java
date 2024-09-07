package com.quick_bites.dto.paymentdto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenerateSignatureDto {

    private String orderId;

    private String paymentId;

    private String signature;

}
