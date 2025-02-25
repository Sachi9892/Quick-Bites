package com.quick_bites.controllers.user_controller;


import com.quick_bites.dto.addressdto.AddressResponseDto;
import com.quick_bites.jwt.JwtUtils;
import com.quick_bites.service.user_profile.IFetchUserAddresses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class AddressesController {

    private final IFetchUserAddresses userAddressesService;
    private final JwtUtils jwtUtils;

    @GetMapping("/profile/addresses")
    public ResponseEntity<List<AddressResponseDto>> getAddressesByUserId(@RequestHeader("Authorization") String token) {

        Long userId = jwtUtils.extractUserId(token.replace("Bearer ", ""));

        List<AddressResponseDto> addresses = userAddressesService.findAllAddress(userId);

        if (addresses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(addresses);
    }
}
