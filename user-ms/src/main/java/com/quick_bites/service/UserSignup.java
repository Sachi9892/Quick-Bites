package com.quick_bites.service;

import com.quick_bites.entity.User;

public interface UserSignup {

    User signUp(String name , String mobileNumber , String address , String email);

}
