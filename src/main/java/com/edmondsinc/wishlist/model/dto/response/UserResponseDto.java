package com.edmondsinc.wishlist.model.dto.response;

import com.edmondsinc.wishlist.model.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class UserResponseDto extends ResponseBaseDto {
    List<UserDto> userDtoList;

    public UserResponseDto(List<UserDto> userDtoList) {
        this.userDtoList = userDtoList;
    }

    public UserResponseDto() {
    }

    public List<UserDto> getUserDtoList() {
        return userDtoList;
    }

    public void setUserDtoList(List<UserDto> userDtoList) {
        this.userDtoList = userDtoList;
    }
}
