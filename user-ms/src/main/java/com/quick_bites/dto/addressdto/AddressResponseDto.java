package com.quick_bites.dto.addressdto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressResponseDto implements Serializable {

    private Long deliveryAddressId;
    private String name;
    private String plotNo;
    private String landmark;
    private Double latitude;
    private Double longitude;
    private String userAddress;
    private Long userId;

}
