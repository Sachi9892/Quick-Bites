package com.quick_bites.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserDto {

    private Long userId;

    private String userName;

    private String userEmail;

    private String userMobileNumber;

    private String userAddress;

}
