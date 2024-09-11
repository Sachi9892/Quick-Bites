package com.quick_bites.service.user_profile;

import com.quick_bites.dto.addressdto.AddressResponseDto;

import java.util.List;

public interface IFetchUserAddresses {

    List<AddressResponseDto> findAllAddress(Long userId);

}
