package com.myservice.usermanageservice.service;

import com.myservice.usermanageservice.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto saveUser(UserDto userDto);

    UserDto getUserById(long userId);

    List<UserDto> getAllUsers();
}
