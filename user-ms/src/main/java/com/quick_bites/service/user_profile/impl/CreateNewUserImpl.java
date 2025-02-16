package com.quick_bites.service.user_profile.impl;

import com.quick_bites.dto.AddUserDto;
import com.quick_bites.dto.location_dto.LocationDto;
import com.quick_bites.entity.DeliveryAddresses;
import com.quick_bites.entity.User;
import com.quick_bites.repository.UserRepository;
import com.quick_bites.service.managers.dish_rendering_manager.feign_client.RestaurantClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
@AllArgsConstructor
public class CreateNewUserImpl implements com.quick_bites.service.user_profile.ICreateNewUser {

    private final UserRepository userRepository;
    private final RestaurantClient restaurantClient;

    @Override
    public String newUser(AddUserDto addUserDto) {

        User userToCheck = userRepository.findByUserMobileNumber(addUserDto.getUserMobileNumber());

        //User is already present
        if(userToCheck != null) {
            return "Welcome , Please proceed to login";
        }

        //Create object of send otp
        //RequestOtpDto sendOtpDto = new RequestOtpDto(addUserDto.getUserMobileNumber() , OtpCases.USER_SIGNUP);


       //Send otp
         //otpClient.sendOtp(sendOtpDto);


         //Check if otp got verified



        //if yes , proceed to save user


        User user = new User();

        user.setUserName(addUserDto.getUserName());
        user.setUserEmail(addUserDto.getUserEmail());
        user.setUserMobileNumber(addUserDto.getUserMobileNumber());

        user.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

        String address = addUserDto.getAddress();

        //Convert address into latitude and longitude
        LocationDto coordinates = restaurantClient.getCoordinates(address);

        DeliveryAddresses deliveryAddress = new DeliveryAddresses();

        deliveryAddress.setLatitude(coordinates.getLatitude());
        deliveryAddress.setLongitude(coordinates.getLongitude());
        deliveryAddress.setUserAddress(address);

        deliveryAddress.setUser(user);

        user.setDeliveryAddresses(List.of(deliveryAddress));

        userRepository.save(user);

        return "Welcome to quick-bites , your craving partner !";

    }

}
