package com.quick_bites.service.user_profile.impl;

import com.quick_bites.dto.user_dto.LoginUserDto;
import com.quick_bites.entity.User;
import com.quick_bites.exceptions.UserNotFoundException;
import com.quick_bites.repository.UserRepository;
import com.quick_bites.service.user_profile.ILogInUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class LogInServiceImpl implements ILogInUserService {

    private final UserRepository userRepository;

    @Override
    public User logIn(LoginUserDto loginUserDto) {

        return userRepository.findByUserMobileNumberAndPassword(loginUserDto.getMobileNumber(), loginUserDto.getPassword())
                .orElseThrow(() -> new UserNotFoundException("No user found. Please sign up."));

    }
}
