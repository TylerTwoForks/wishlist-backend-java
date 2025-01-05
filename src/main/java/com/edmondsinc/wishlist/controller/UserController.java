package com.edmondsinc.wishlist.controller;

import com.edmondsinc.wishlist.model.dto.CreateUserDto;
import com.edmondsinc.wishlist.model.dto.UserDto;
import com.edmondsinc.wishlist.model.dto.response.ResponseBaseDto;
import com.edmondsinc.wishlist.model.dto.response.UserResponseDto;
import com.edmondsinc.wishlist.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
    public ResponseEntity<?> getAll()
    {
        List<UserDto> userDtos = userService.getAllUsers();
        if(userDtos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDtos);
    }

    /**
     *
     * @param userGuid guid on the user_account table for record you want
     * @return ResponseBaseDto - could be a UserResponseDto or a ResponseBaseDto depending on result.
     */
    @GetMapping(path="/{userGuid}")
    public ResponseBaseDto getUserById(
            @PathVariable UUID userGuid
    )
    {
        return userService.getUser(userGuid);
    }


    @DeleteMapping(path = "/delete/{userGuid}")
    public ResponseBaseDto deleteUserByGuid(@PathVariable UUID userGuid){
        return userService.deleteUser(userGuid);
    }

}
