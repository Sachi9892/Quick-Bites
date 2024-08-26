package com.quick_bites.controllers;


import com.quick_bites.dto.AddUserDto;
import com.quick_bites.service.user_signup.CreateNewUser;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class CreateUserController {

    private final CreateNewUser createNewUser;

    @PostMapping("/new")

    public ResponseEntity<String> newUser(@RequestBody AddUserDto userDto) {
        String res = createNewUser.newUser(userDto);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

}
