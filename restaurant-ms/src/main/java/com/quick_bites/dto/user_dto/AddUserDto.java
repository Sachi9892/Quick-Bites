package com.quick_bites.dto.user_dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserDto {

    private String name;

    private String mobileNumber;

    private String address;
}
