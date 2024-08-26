package com.quick_bites.service.user_signup.impl;

import com.quick_bites.dto.AddUserDto;
import com.quick_bites.entity.User;
import com.quick_bites.repository.UserRepository;
import com.quick_bites.service.user_signup.CreateNewUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@AllArgsConstructor
public class ICreateNewUser implements CreateNewUser  {

    private final UserRepository userRepository;

    @Override
    public String newUser(AddUserDto addUserDto) {

        User user = new User();

        user.setUserId(addUserDto.getUserId());

        user.setUserName(addUserDto.getUserName());
        user.setUserEmail(addUserDto.getUserEmail());
        user.setUserAddress(addUserDto.getUserAddress());
        user.setUserMobileNumber(addUserDto.getUserMobileNumber());
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);

        return "Welcome to quick-bites , your craving partner !";
    }

}
