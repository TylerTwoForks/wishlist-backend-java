package com.edmondsinc.wishlist.service;

import com.edmondsinc.wishlist.model.User;
import com.edmondsinc.wishlist.model.dto.CreateUserDto;
import com.edmondsinc.wishlist.model.dto.UserDto;
import com.edmondsinc.wishlist.model.dto.response.ResponseBaseDto;
import com.edmondsinc.wishlist.model.dto.response.UserResponseDto;
import com.edmondsinc.wishlist.repository.UserRepo;
import com.edmondsinc.wishlist.util.EncryptionUtil;
import jakarta.persistence.PersistenceException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public ResponseBaseDto getUser(UUID userGuid) {
        User user = userRepo.findByGuidAndActiveTrue(userGuid);
        return user != null ?
                new UserResponseDto(List.of(user.toUserDto())).ok() :
                new ResponseBaseDto(204, HttpStatus.NO_CONTENT, "No user found for userGuid: [" + userGuid+"]");
    }

    public List<UserDto> getAllUsers() {
        List<User> userList = userRepo.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        if (!userList.isEmpty()) {
            userList.forEach(u -> userDtoList.add(new UserDto(u)));
        }
        return userDtoList;
    }


    public UserResponseDto createUser(CreateUserDto createUserDto) {
        User user = new User(createUserDto);

        //password hashing here
        EncryptionUtil.encyptPW(user, createUserDto);

        try {
            userRepo.save(user);
        } catch (DataAccessException dae) {
            throw new PersistenceException("Unable to save new user due to: "+dae.getMessage());
        }
        return new UserResponseDto(List.of(user.toUserDto()));
    }


    @Transactional
    public ResponseBaseDto deleteUser(UUID userGuid){
        Integer result = userRepo.deleteUserByGuid(userGuid);
        return result == 0 ?
                new ResponseBaseDto(404, HttpStatus.NOT_FOUND, "No user found with this UUID") :
                new UserResponseDto(204, HttpStatus.NO_CONTENT, "Delete Succesful");

    }
}
