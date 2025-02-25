package com.quick_bites.service.user_profile;

import com.quick_bites.dto.user_dto.AddUserDto;
import com.quick_bites.entity.User;

public interface ICreateNewUser {

    User newUser(AddUserDto addUserDto);

}
