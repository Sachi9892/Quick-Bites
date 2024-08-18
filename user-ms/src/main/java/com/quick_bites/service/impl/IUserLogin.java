package com.quick_bites.service.impl;

import com.quick_bites.entity.User;
import com.quick_bites.repository.UserRepository;
import com.quick_bites.service.UserLogin;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class IUserLogin implements UserLogin {

    private final UserRepository userRepository;

    @Override
    public User logIn(String mobileNumber, String otp) {

        return null;

    }
}
