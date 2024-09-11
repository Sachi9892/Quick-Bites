package com.quick_bites.dto.addressdto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AddressResponseDto {

    private Long deliveryAddressId;
    private String name;
    private String plotNo;
    private String landmark;
    private Double latitude;
    private Double longitude;
    private String userAddress;
    private Long userId;

}
