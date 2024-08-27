package com.quick_bites.service.user_signup.impl;

import com.quick_bites.dto.AddUserDto;
import com.quick_bites.dto.LocationDto;
import com.quick_bites.entity.Address;
import com.quick_bites.entity.User;
import com.quick_bites.repository.UserRepository;
import com.quick_bites.service.managers.dish_rendering_manager.feign_client.DishClient;
import com.quick_bites.service.user_signup.CreateNewUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@AllArgsConstructor
public class ICreateNewUser implements CreateNewUser  {

    private final UserRepository userRepository;
    private final DishClient dishClient;

    @Override
    public String newUser(AddUserDto addUserDto) {

        User user = new User();

        user.setUserName(addUserDto.getUserName());
        user.setUserEmail(addUserDto.getUserEmail());
        user.setUserMobileNumber(addUserDto.getUserMobileNumber());
        user.setCreatedAt(LocalDateTime.now());


        //Address
        LocationDto coordinates = dishClient.getCoordinates(addUserDto.getAddress());

        Address address = new Address(coordinates.getLatitude(), coordinates.getLongitude(), addUserDto.getAddress());

        user.setAddress(address);

        userRepository.save(user);

        return "Welcome to quick-bites , your craving partner !";


    }

}
