package com.quick_bites.services.user_service;

import com.quick_bites.dto.location_dto.LocationDto;
import com.quick_bites.dto.user_dto.AddUserDto;
import com.quick_bites.entity.Location;
import com.quick_bites.entity.User;
import com.quick_bites.location_service.GeoCodingService;
import com.quick_bites.repository.user_repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final GeoCodingService geoCodingService;

    public User addUser(AddUserDto addUserDto) {

        LocationDto coordinates = geoCodingService.getCoordinates(addUserDto.getAddress());

        User user = new User();

        user.setName(addUserDto.getName());
        user.setMobileNumber(addUserDto.getMobileNumber());
        user.setLocation(new Location(coordinates.getLatitude(), coordinates.getLongitude(), addUserDto.getAddress()));
        return userRepository.save(user);
    }

}
