package com.quick_bites.dto.paymentdto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenerateSignatureDto implements Serializable {

    private String orderId;

    private String paymentId;

    private String signature;

}
