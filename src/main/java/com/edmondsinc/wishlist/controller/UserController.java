package com.edmondsinc.wishlist.controller;

import com.edmondsinc.wishlist.model.dto.CreateUserDto;
import com.edmondsinc.wishlist.model.dto.response.ResponseBaseDto;
import com.edmondsinc.wishlist.model.dto.response.UserResponseDto;
import com.edmondsinc.wishlist.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public UserResponseDto createUser(
            @RequestBody CreateUserDto newUser
    )
    {
        return userService.createUser(newUser);
    }

    @GetMapping(path="/all")
    public ResponseBaseDto getAll()
    {
        return userService.getAllUsers();
    }

    @GetMapping(path="/{userGuid}")
    public ResponseBaseDto getUserById(
            @PathVariable UUID userGuid
    )
    {
        return userService.getUser(userGuid);
    }


}
