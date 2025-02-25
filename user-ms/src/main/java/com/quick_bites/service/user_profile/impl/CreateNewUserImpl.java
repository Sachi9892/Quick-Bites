package com.quick_bites.service.user_profile.impl;

import com.quick_bites.dto.user_dto.AddUserDto;
import com.quick_bites.dto.location_dto.LocationDto;
import com.quick_bites.entity.DeliveryAddresses;
import com.quick_bites.entity.User;
import com.quick_bites.exceptions.UserAlreadyExistsException;
import com.quick_bites.repository.UserRepository;
import com.quick_bites.service.managers.dish_rendering_manager.feign_client.RestaurantClient;
import com.quick_bites.service.user_profile.ICreateNewUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
@AllArgsConstructor
public class CreateNewUserImpl implements ICreateNewUser {

    private final UserRepository userRepository;
    private final RestaurantClient restaurantClient;

    @Override
    public User newUser(AddUserDto addUserDto) {

        User userToCheck = userRepository.findByUserMobileNumber(addUserDto.getUserMobileNumber());

        // User is already present
        if (userToCheck != null) {
            throw new UserAlreadyExistsException("User already exists. Please proceed to login.");
        }


        User user = new User();

        user.setUserName(addUserDto.getUserName());
        user.setUserEmail(addUserDto.getUserEmail());
        user.setUserMobileNumber(addUserDto.getUserMobileNumber());
        user.setPassword(addUserDto.getPassword());
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

        return userRepository.save(user);

    }

}
