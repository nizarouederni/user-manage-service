package com.myservice.usermanageservice.controller;

import com.myservice.usermanageservice.dto.UserDto;
import com.myservice.usermanageservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder ;

    // Build Save User Rest API
    @PostMapping
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto){
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        UserDto saveUserDto = userService.saveUser(userDto);
        return new ResponseEntity<>(saveUserDto, HttpStatus.OK);
    }

    // Build Get User Rst API
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") long userId){
        UserDto userDto=userService.getUserById(userId);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    // Build Get All Users Rest API
    @GetMapping("")
    public  ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> userDtos = userService.getAllUsers();
        return new ResponseEntity<>(userDtos,HttpStatus.OK);
    }

    // Build Get User Rst API
    @GetMapping("/password/{password}")
    public ResponseEntity<String> getPassword(@PathVariable("password") String password){
        String encodePassword= passwordEncoder.encode(password);
        System.out.println("encodePassword = " + encodePassword);
        System.out.println("passwordEncoder.matches(password,encodePassword) = " + passwordEncoder.matches(password,encodePassword));
        return new ResponseEntity<>(encodePassword,HttpStatus.OK);
    }



}
