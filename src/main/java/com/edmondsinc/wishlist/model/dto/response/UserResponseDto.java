package com.edmondsinc.wishlist.model.dto.response;

import com.edmondsinc.wishlist.model.User;
import com.edmondsinc.wishlist.model.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserResponseDto extends ResponseBaseDto {
    List<UserDto> userDtoList;

    public UserResponseDto() {}

    public UserResponseDto(Integer httpCode, HttpStatus httpStatus, String message) {
        super(httpCode, httpStatus, message);
    }

    public UserResponseDto(List<UserDto> userDtoList) {
        this.userDtoList = userDtoList;
    }

    public UserResponseDto(List<UserDto> userDtoList, Integer httpCode, HttpStatus httpStatus, String message) {
        super(httpCode, httpStatus, message);
        this.userDtoList = userDtoList;
    }



    public UserResponseDto ok(){
        this.setHttpCode(200);
        this.setHttpStatus(HttpStatus.OK);

        return this;
    }

    public List<UserDto> getUserDtoList() {
        return userDtoList;
    }

    public void setUserDtoList(List<UserDto> userDtoList) {
        this.userDtoList = userDtoList;
    }
}
