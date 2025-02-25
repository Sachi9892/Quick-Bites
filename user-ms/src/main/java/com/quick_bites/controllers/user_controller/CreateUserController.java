package com.quick_bites.controllers.user_controller;


import com.quick_bites.dto.user_dto.AddUserDto;
import com.quick_bites.entity.User;
import com.quick_bites.exceptions.UserAlreadyExistsException;
import com.quick_bites.jwt.JwtUtils;
import com.quick_bites.repository.UserRepository;
import com.quick_bites.service.user_profile.ICreateNewUser;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class CreateUserController {

    private final ICreateNewUser createNewUser;
    private final JwtUtils jwtUtils;

    @PostMapping("/signup")
    public ResponseEntity<?> newUser(@RequestBody AddUserDto userDto) {

        try {
            User user = createNewUser.newUser(userDto); // Create new user
            String token = jwtUtils.generateToken(user.getUserId()); // Generate token
            return ResponseEntity.ok(Map.of(
                    "message", "Welcome to quick-bites, your craving partner!",
                    "token", token
            )); // Return token in response
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
