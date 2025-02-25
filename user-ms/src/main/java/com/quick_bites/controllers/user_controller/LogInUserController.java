package com.quick_bites.controllers.user_controller;


import com.quick_bites.dto.user_dto.LoginUserDto;
import com.quick_bites.entity.User;
import com.quick_bites.exceptions.UserNotFoundException;
import com.quick_bites.jwt.JwtUtils;
import com.quick_bites.service.user_profile.ILogInUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
@Slf4j
public class LogInUserController {

    private final JwtUtils jwtUtils;
    private final ILogInUserService logInUserService;

    @PostMapping("/login")
    public ResponseEntity<?> logInController(@RequestBody LoginUserDto userDto) {

        try {
            User user = logInUserService.logIn(userDto); // Authenticate user
            String token = jwtUtils.generateToken(user.getUserId()); // Generate JWT
            log.info("Token : {}" , token);
            return ResponseEntity.ok(Map.of("token", token)); // Return token in response
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found. Please sign up.");
        }

    }

}
