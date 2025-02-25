package com.quick_bites.dto.paymentdto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDto implements Serializable {

    private Double amount;
    private String receiptId;

}
