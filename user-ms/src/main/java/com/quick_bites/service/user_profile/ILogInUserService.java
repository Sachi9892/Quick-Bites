package com.quick_bites.service.user_profile;

import com.quick_bites.dto.user_dto.LoginUserDto;
import com.quick_bites.entity.User;

public interface ILogInUserService {


    User logIn(LoginUserDto loginUserDto);

}
