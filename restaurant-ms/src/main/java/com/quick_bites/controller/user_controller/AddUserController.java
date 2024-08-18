package com.quick_bites.controller.user_controller;


import com.quick_bites.dto.user_dto.AddUserDto;
import com.quick_bites.dto.user_dto.ResponseUserDto;
import com.quick_bites.entity.User;
import com.quick_bites.services.user_service.UserService;
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
public class AddUserController {

    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<ResponseUserDto> addUser(@RequestBody AddUserDto addUserDto) {

        ResponseUserDto userDto = new ResponseUserDto();
        User user = userService.addUser(addUserDto);
        userDto.setName(user.getName());
        userDto.setMobileNumber(user.getMobileNumber());

        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);

    }
}
