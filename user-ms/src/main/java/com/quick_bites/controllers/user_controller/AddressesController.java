package com.quick_bites.controllers.user_controller;


import com.quick_bites.dto.addressdto.AddressResponseDto;
import com.quick_bites.service.user_profile.IFetchUserAddresses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class AddressesController {

    private final IFetchUserAddresses userAddressesService;

    @GetMapping("/addresses")
    public ResponseEntity<List<AddressResponseDto>> getAddressesByUserId(@RequestParam Long userId) {

        List<AddressResponseDto> addresses = userAddressesService.findAllAddress(userId);

        if (addresses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(addresses);
    }
}
