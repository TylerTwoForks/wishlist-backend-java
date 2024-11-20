package com.edmondsinc.wishlist.mock;

import com.edmondsinc.wishlist.model.User;
import com.edmondsinc.wishlist.model.dto.CreateUserDto;
import com.edmondsinc.wishlist.model.dto.UserDto;
import com.edmondsinc.wishlist.model.dto.response.UserResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MockData {

    public User user;
    public User user2;
    public List<User> userList;
    public List<UserDto> userDtoList;
    public UserResponseDto userResponseDto = new UserResponseDto();
    public CreateUserDto createUserDto;

    public MockData(){this.init();}

    public void init(){
        createUserDto = new CreateUserDto("Tyler", "Edmonds Test", "tyler@tyler.com", "testPassword");
        user = new User.Builder().setFirstName("Tyler").setLastName("Edmonds Test").setUserName("tyler@tyler.com").build();
        user2 = new User.Builder().setUserName("tyler2@tyler2.com").build();
        userList = List.of(user, user2);
        userDtoList = userList.stream().map(User::toUserDto).collect(Collectors.toList());
        userResponseDto.setUserDtoList(userDtoList);

    }
}
