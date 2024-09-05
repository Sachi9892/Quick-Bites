package com.quick_bites.dto.paytmdto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentVerificationDto {

    private String orderId;
    private String paymentId;
    private String signature;

}
