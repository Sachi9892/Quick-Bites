package com.quick_bites.dto.paytmdto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDto {

    private Double amount;
    private String receiptId;

}
