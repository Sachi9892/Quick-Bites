package com.quick_bites.service;

import com.quick_bites.entity.User;

public interface UserLogin {

    //log in for user
    User  logIn(String mobileNumber , String otp);

}
