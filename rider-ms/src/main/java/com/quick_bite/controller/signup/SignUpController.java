package com.quick_bite.controller.signup;


import com.quick_bite.dto.rider_dto.RiderSignUpDTO;
import com.quick_bite.entity.Rider;
import com.quick_bite.service.signup.RiderSignUpService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rider")
@AllArgsConstructor
public class SignUpController {

    private final RiderSignUpService signUpService;

    @PostMapping("/signup")
    public ResponseEntity<Rider> signUp(@RequestBody RiderSignUpDTO dto) {

        Rider rider = signUpService.signUpRider(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(rider);

    }
}
