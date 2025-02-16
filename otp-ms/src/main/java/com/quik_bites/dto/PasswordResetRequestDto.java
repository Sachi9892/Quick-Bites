package com.quik_bites.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetRequestDto {

    private String phoneNumber;
    private String userName;
    private String oneTimePassword;

}
