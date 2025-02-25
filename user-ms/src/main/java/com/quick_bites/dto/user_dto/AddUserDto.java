package com.quick_bites.dto.user_dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserDto {

    private String userName;
    private String userEmail;
    private String password;
    private String confirmPassword;
    private String userMobileNumber;
    private String address;

}
