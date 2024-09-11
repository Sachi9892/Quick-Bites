package com.quick_bites.mapper;

import com.quick_bites.dto.addressdto.AddressResponseDto;
import com.quick_bites.entity.DeliveryAddresses;

public class AddressesMapper {

    private AddressesMapper() {

    }

    public static AddressResponseDto addressToAddressDto(DeliveryAddresses addresses) {

        return AddressResponseDto.builder()
                .deliveryAddressId(addresses.getDeliveryAddressId())
                .name(addresses.getName())
                .plotNo(addresses.getPlotNo())
                .landmark(addresses.getLandmark())
                .latitude(addresses.getLatitude())
                .longitude(addresses.getLongitude())
                .userAddress(addresses.getUserAddress())
                .userId(addresses.getUser().getUserId())
                .build();
    }

}
