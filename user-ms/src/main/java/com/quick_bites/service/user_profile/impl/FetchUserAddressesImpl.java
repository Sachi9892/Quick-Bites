package com.quick_bites.service.user_profile.impl;


import com.quick_bites.dto.addressdto.AddressResponseDto;
import com.quick_bites.entity.DeliveryAddresses;
import com.quick_bites.mapper.AddressesMapper;
import com.quick_bites.repository.DeliveryAddressRepository;
import com.quick_bites.service.user_profile.IFetchUserAddresses;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FetchUserAddressesImpl implements IFetchUserAddresses {

    private final DeliveryAddressRepository addressRepository;

    @Override
    @Cacheable(value = "user_address" , key = "{#userId}")
    public List<AddressResponseDto> findAllAddress(Long userId) {
        List<DeliveryAddresses> addresses = addressRepository.findAddressesByUser_UserId(userId);
        return addresses.stream().map(AddressesMapper::addressToAddressDto).toList();

    }
}
