package com.myservice.usermanageservice.service.impl;

import com.myservice.usermanageservice.dto.UserDto;
import com.myservice.usermanageservice.entity.User;
import com.myservice.usermanageservice.repository.UserRepository;
import com.myservice.usermanageservice.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public UserDto saveUser(UserDto userDto) {
        User user= userRepository.save(modelMapper.map(userDto,User.class));

        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public UserDto getUserById(long userId) {
        return modelMapper.map(userRepository.getUserById(userId),UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users =userRepository.findAll();

        return users.stream().map((user -> modelMapper.map(user,UserDto.class))).collect(Collectors.toList());
    }
}
