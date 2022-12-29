package com.myservice.usermanageservice.service.impl;

import com.myservice.usermanageservice.dto.UserDto;
import com.myservice.usermanageservice.entity.User;
import com.myservice.usermanageservice.repository.UserRepository;
import com.myservice.usermanageservice.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @InjectMocks
    private UserService userServiceMocks;
    @Mock
    private UserRepository userRepositoryMocks;

    private ModelMapper modelMapper;


    @BeforeEach
    public void init() {
        System.out.println("Before");
        modelMapper=new ModelMapper();
        userRepositoryMocks=mock(UserRepository.class);
        userServiceMocks= new UserServiceImpl(userRepositoryMocks,modelMapper);


    }
    @AfterEach
    public void teardown() {
        System.out.println("After");
    }
    
    @Test
    void saveUser() {
        User user = new User(1L,"Ali1","Med","ali@gmail.com","DMV","123456789","Pass123");
        when(userRepositoryMocks.save(any(User.class))).thenReturn(user);

        UserDto userDtoSaved = userServiceMocks.saveUser(modelMapper.map(user,UserDto.class));

        assertThat(1l).isEqualTo(userDtoSaved.getId());
        assertThat("Ali1").isEqualTo(userDtoSaved.getFirstName());
    }

    @Test
    void getUserById() {
        when(userRepositoryMocks.getUserById(1l)).thenReturn(new User(1L,"Ali1","Med","ali@gmail.com","DMV","123456789","Pass123"));
        //Test
        UserDto userDto= userServiceMocks.getUserById(1);
        assertThat(1l).isEqualTo(userDto.getId());
        assertThat("Ali1").isEqualTo(userDto.getFirstName());
    }

    @Test
    void getAllUsers() {
        System.out.println("Test");
        List<User> userDtoList= new ArrayList<User>();
        userDtoList.add(new User(1L,"Ali1","Med","ali@gmail.com","DMV","123456789","Pass123"));
        userDtoList.add(new User(2L,"Ali2","Med","ali@gmail.com","DMV","123456789","Pass123"));
        userDtoList.add(new User(3L,"Ali3","Med","ali@gmail.com","DMV","123456789","Pass123"));

        when(userRepositoryMocks.findAll()).thenReturn(userDtoList);
        //test
        List<UserDto> userDtos=userServiceMocks.getAllUsers();
        assertThat(userDtos.size()).isEqualTo(3);

    }
}